package com.recargapay.wallet.infrastructure.adapter.input.rest.util;

import lombok.Getter;

@Getter
public enum Action {

	DEPOSIT("deposit"),
	WITHDRAW("withdraw"),
	TRANSFER("transfer");
	
	private final String value;
	
	Action(String value) {
		this.value = value;
	}
	
}
