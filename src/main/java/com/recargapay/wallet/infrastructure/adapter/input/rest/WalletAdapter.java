package com.recargapay.wallet.infrastructure.adapter.input.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recargapay.wallet.application.port.input.WalletUseCase;
import com.recargapay.wallet.domain.model.HistoricalWallet;
import com.recargapay.wallet.domain.model.Wallet;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.FundsRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.WalletRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.validation.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@Tag(name = "Wallet Adapter")
@RestController
@RequestMapping("/v1/wallet")
@RequiredArgsConstructor
public class WalletAdapter {

	private final WalletUseCase walletUseCase;

	@Operation(summary = "Allow the creation of wallets for users.")
	@PostMapping
	public ResponseEntity<Wallet> createWallet(@RequestBody WalletRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(walletUseCase.createWallet(request));
	}

	@Operation(summary = " Retrieve the current balance of a user's wallet.")
	@GetMapping("/balance/{accNumber}")
	public ResponseEntity<BigDecimal> retrieveBalance(@UUID @PathVariable("accNumber") String accNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(walletUseCase.retrieveBalance(accNumber));
	}

	@Operation(summary = "Retrieve the balance of a user's wallet at a specific point in the past.")
	@GetMapping("/balance/historic/{accNumber}")
	public ResponseEntity<List<HistoricalWallet>> retrieveHistoricalBalance(@UUID @PathVariable("accNumber") String accNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(walletUseCase.retrieveHistoricalBalance(accNumber));
	}

	@Operation(summary = "Enable users to deposit money into their wallets")
	@PostMapping("/deposit/funds")
	public ResponseEntity<Void> depositFunds(@Valid @RequestBody FundsRequest request) {
		walletUseCase.depositFunds(request);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Operation(summary = "Enable users to withdraw money from their wallets.")
	@PostMapping("/withdraw/funds")
	public ResponseEntity<Void> withdrawFunds(@Valid @RequestBody FundsRequest request) {
		walletUseCase.withdrawFunds(request);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Operation(summary = "Facilitate the transfer of money between user wallets.")
	@PostMapping("/transfer/funds")
	public ResponseEntity<Void> transferFunds(@Valid @RequestBody FundsRequest request) {
		walletUseCase.transferFunds(request);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
