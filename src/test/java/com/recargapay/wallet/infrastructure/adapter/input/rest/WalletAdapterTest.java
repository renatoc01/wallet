package com.recargapay.wallet.infrastructure.adapter.input.rest;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.http.HttpStatus;

import com.recargapay.wallet.application.port.input.WalletUseCase;
import com.recargapay.wallet.domain.model.HistoricalWallet;
import com.recargapay.wallet.domain.model.Wallet;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.FundsRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.WalletRequest;

@ExtendWith(MockitoExtension.class)
public class WalletAdapterTest {

	@InjectMocks
	private WalletAdapter adapter;

	@Mock
	private WalletUseCase walletUseCase;

	private WalletRequest request = new WalletRequest();
	private Wallet wallet = new Wallet();
	private FundsRequest fundsRequest = new FundsRequest();
	private HistoricalWallet historicalWallet = new HistoricalWallet();
	private List<HistoricalWallet> list = new ArrayList<>();
	private static final String UUID = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc";

	@BeforeEach
	void setUp() {
		request.setUsername("TEST");
		wallet.setUsername("TEST");
		fundsRequest.setAmount(new BigDecimal(1));
		historicalWallet.setAmount(new BigDecimal(1));
		list.add(historicalWallet);
	}

	@Test
	void createWalletTest() {

		when(walletUseCase.createWallet(request)).thenReturn(wallet);

		var response = adapter.createWallet(request);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(request.getUsername(), requireNonNull(response.getBody().getUsername()));
		verify(walletUseCase, times(1)).createWallet(request);

	}

	@Test
	void retrieveBalanceTest() {

		when(walletUseCase.retrieveBalance(UUID)).thenReturn(new BigDecimal(1));

		var response = adapter.retrieveBalance(UUID);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(walletUseCase, times(1)).retrieveBalance(UUID);

	}

	@Test
	void retrieveHistoricalBalanceTest() {

		when(walletUseCase.retrieveHistoricalBalance(UUID)).thenReturn(list);

		var response = adapter.retrieveHistoricalBalance(UUID);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(walletUseCase, times(1)).retrieveHistoricalBalance(UUID);

	}

	@Test
	void depositFundsTest() {

		doNothing().when(walletUseCase).depositFunds(fundsRequest);

		var response = adapter.depositFunds(fundsRequest);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(walletUseCase, times(1)).depositFunds(fundsRequest);

	}

	@Test
	void withdrawFundsTest() {

		doNothing().when(walletUseCase).withdrawFunds(fundsRequest);

		var response = adapter.withdrawFunds(fundsRequest);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(walletUseCase, times(1)).withdrawFunds(fundsRequest);

	}

	@Test
	void transferFundsTest() {

		doNothing().when(walletUseCase).transferFunds(fundsRequest);

		var response = adapter.transferFunds(fundsRequest);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(walletUseCase, times(1)).transferFunds(fundsRequest);

	}

}
