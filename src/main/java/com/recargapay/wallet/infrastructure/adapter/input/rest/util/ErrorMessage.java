package com.recargapay.wallet.infrastructure.adapter.input.rest.util;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	WALLET_NOT_FOUND("Wallet not found"),
	INVALID_DEPOSIT("Invalid deposit amount"),
	INSUFFICIENT_BALANCE("Insufficient balance"),
	HISTORICAL_WALLET_NOT_FOUND("Historical wallet not found");
	
	private final String value;
	
	ErrorMessage(String value) {
		this.value = value;
	}
	
}
