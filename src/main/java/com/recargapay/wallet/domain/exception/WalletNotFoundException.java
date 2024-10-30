package com.recargapay.wallet.domain.exception;

@SuppressWarnings("serial")
public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String message) {
        super(message);
    }

}
