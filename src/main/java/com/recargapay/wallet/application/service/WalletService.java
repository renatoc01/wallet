package com.recargapay.wallet.application.service;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recargapay.wallet.application.port.input.WalletUseCase;
import com.recargapay.wallet.application.port.output.HistoricalWalletOutputPort;
import com.recargapay.wallet.application.port.output.WalletOutputPort;
import com.recargapay.wallet.domain.exception.WalletNotFoundException;
import com.recargapay.wallet.domain.model.HistoricalWallet;
import com.recargapay.wallet.domain.model.Wallet;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.FundsRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.WalletRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.mapper.HistoricalWalletMapper;
import com.recargapay.wallet.infrastructure.adapter.input.rest.mapper.WalletMapper;
import com.recargapay.wallet.infrastructure.adapter.input.rest.util.AccountNumber;
import com.recargapay.wallet.infrastructure.adapter.input.rest.util.Action;
import com.recargapay.wallet.infrastructure.adapter.input.rest.util.ErrorMessage;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class WalletService implements WalletUseCase {

	private final WalletOutputPort walletOutputport;
	private final HistoricalWalletOutputPort historicalWalletOutputPort;
	private final WalletMapper walletMapper;
	private final HistoricalWalletMapper historicalWalletMapper;

	@Override
	public Wallet createWallet(WalletRequest request) {
		var accNumber = AccountNumber.generateAccountNumber();
		var walletEntity = WalletEntity.builder().username(request.getUsername()).accNumber(accNumber)
				.balance(new BigDecimal(0)).build();
		walletOutputport.save(walletEntity);
		log.info("Create wallet " + accNumber);
		return walletMapper.toWallet(walletEntity);
	}

	@Override
	public BigDecimal retrieveBalance(String accNumber) {
		var wallet = walletOutputport.findByAccNumber(accNumber);
		if (isNull(wallet)) {
			throw new WalletNotFoundException(ErrorMessage.WALLET_NOT_FOUND.getValue());
		}
		return wallet.getBalance();
	}

	@Override
	public List<HistoricalWallet> retrieveHistoricalBalance(String accNumber) {
		var items = historicalWalletOutputPort.findByWalletAccNumber(accNumber);
		if (isNull(items) || items.isEmpty()) {
			throw new WalletNotFoundException(ErrorMessage.HISTORICAL_WALLET_NOT_FOUND.getValue());
		}
		return historicalWalletMapper.map(items);
	}

	@Transactional
	@Override
	public void depositFunds(FundsRequest request) {
		var wallet = walletOutputport.findByAccNumber(request.getSourceAccNumber());
		if (isNull(wallet)) {
			throw new WalletNotFoundException(ErrorMessage.WALLET_NOT_FOUND.getValue());
		}
		wallet.deposit(request.getAmount());
		walletOutputport.save(wallet);
		var historic = new HistoricalWalletEntity();
		historicalWalletOutputPort.save(historic.save(wallet, Action.DEPOSIT.getValue(), request.getAmount()));
		log.info("Deposit funds " + request.getAmount() + " on the wallet " + wallet.getAccNumber());
	}

	@Transactional
	@Override
	public void withdrawFunds(FundsRequest request) {
		var wallet = walletOutputport.findByAccNumber(request.getSourceAccNumber());
		if (isNull(wallet)) {
			throw new WalletNotFoundException(ErrorMessage.WALLET_NOT_FOUND.getValue());
		}
		wallet.withdraw(request.getAmount());
		walletOutputport.save(wallet);
		var historic = new HistoricalWalletEntity();
		historicalWalletOutputPort.save(historic.save(wallet, Action.WITHDRAW.getValue(), request.getAmount()));
		log.info("Withdraw funds " + request.getAmount() + " on the wallet " + wallet.getAccNumber());
	}

	@Transactional
	@Override
	public void transferFunds(FundsRequest request) {
		var wallet = walletOutputport.findByAccNumber(request.getSourceAccNumber());
		var destinationWallet = walletOutputport.findByAccNumber(request.getDestinationAccNumber());
		if (isNull(wallet) || isNull(destinationWallet)) {
			throw new WalletNotFoundException(ErrorMessage.WALLET_NOT_FOUND.getValue());
		}
		wallet.withdraw(request.getAmount());
		destinationWallet.deposit(request.getAmount());
		walletOutputport.save(wallet);
		walletOutputport.save(destinationWallet);
		var historic = new HistoricalWalletEntity();
		historicalWalletOutputPort.save(historic.save(wallet, Action.TRANSFER.getValue(), request.getAmount()));
		historicalWalletOutputPort
				.save(historic.save(destinationWallet, Action.TRANSFER.getValue(), request.getAmount()));
		log.info("Withdraw funds " + request.getAmount() + " on the wallet " + wallet.getAccNumber());
		log.info("Deposit funds " + request.getAmount() + " on the wallet " + destinationWallet.getAccNumber());
	}

}
