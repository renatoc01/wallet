package com.recargapay.wallet.infrastructure.adapter.input.rest.util;

import java.util.UUID;

public class AccountNumber {

	public static String generateAccountNumber() {
		return UUID.randomUUID().toString();
	}
	
}
