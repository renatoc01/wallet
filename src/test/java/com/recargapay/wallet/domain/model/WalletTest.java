package com.recargapay.wallet.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class WalletTest {

	@Test
	void wallet() {
		var wallet = new Wallet();
		wallet.setAccNumber("c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc");
		wallet.setBalance(new BigDecimal(1));
		wallet.setId(1L);
		wallet.setUsername("TEST");
		assertNotNull(wallet);
	}

}
