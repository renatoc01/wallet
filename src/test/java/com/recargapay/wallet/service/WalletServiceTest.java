package com.recargapay.wallet.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recargapay.wallet.application.port.output.HistoricalWalletOutputPort;
import com.recargapay.wallet.application.port.output.WalletOutputPort;
import com.recargapay.wallet.application.service.WalletService;
import com.recargapay.wallet.domain.model.Wallet;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.FundsRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.WalletRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.mapper.HistoricalWalletMapper;
import com.recargapay.wallet.infrastructure.adapter.input.rest.mapper.WalletMapper;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

	@InjectMocks
	private WalletService walletService;

	@Mock
	private WalletOutputPort walletOutputPort;

	@Mock
	private HistoricalWalletOutputPort historicalWalletOutputPort;

	@Mock
	private WalletMapper walletMapper;
	
	@Mock
	HistoricalWalletMapper historicalWalletMapper;

	private WalletRequest request = new WalletRequest();
	private Wallet wallet = new Wallet();
	private WalletEntity walletEntity = new WalletEntity();
	private FundsRequest fundsRequest = new FundsRequest();
	private HistoricalWalletEntity historicalWallet = new HistoricalWalletEntity();
	private List<HistoricalWalletEntity> list = new ArrayList<>();
	private static final String UUID = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc";

	@BeforeEach
	void setUp() {
		request.setUsername("TEST");
		wallet.setUsername("TEST");
		walletEntity = WalletEntity.builder().username(request.getUsername()).accNumber(UUID)
				.balance(new BigDecimal(10)).build();
		fundsRequest.setAmount(new BigDecimal(1));
		fundsRequest.setSourceAccNumber(UUID);
		fundsRequest.setDestinationAccNumber(UUID);
		historicalWallet.setAmount(new BigDecimal(1));
		list.add(historicalWallet);
	}

	@Test
	void createWalletTest() {
		when(walletOutputPort.save(any(WalletEntity.class))).thenReturn(any(WalletEntity.class));
		assertDoesNotThrow(() -> walletService.createWallet(request));

	}

	@Test
	void retrieveBalanceTest() {
		when(walletOutputPort.findByAccNumber(UUID)).thenReturn(walletEntity);
		assertDoesNotThrow(() -> walletService.retrieveBalance(UUID));

	}

	@Test
	void retrieveHistoricalBalanceTest() {
		when(historicalWalletOutputPort.findByWalletAccNumber(UUID)).thenReturn(list);
		assertDoesNotThrow(() -> walletService.retrieveHistoricalBalance(UUID));

	}

	@Test
	void depositFundsTest() {
		when(walletOutputPort.findByAccNumber(fundsRequest.getSourceAccNumber())).thenReturn(walletEntity);
		assertDoesNotThrow(() -> walletService.depositFunds(fundsRequest));

	}

	@Test
	void withdrawFundsTest() {
		when(walletOutputPort.findByAccNumber(fundsRequest.getSourceAccNumber())).thenReturn(walletEntity);
		assertDoesNotThrow(() -> walletService.withdrawFunds(fundsRequest));

	}

	@Test
	void transferFundsTest() {
		when(walletOutputPort.findByAccNumber(fundsRequest.getSourceAccNumber())).thenReturn(walletEntity);
		assertDoesNotThrow(() -> walletService.transferFunds(fundsRequest));

	}

}
