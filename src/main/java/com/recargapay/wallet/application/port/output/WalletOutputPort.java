package com.recargapay.wallet.application.port.output;

import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

public interface WalletOutputPort {

	WalletEntity findByAccNumber(final String accNumber);
	
	WalletEntity save(WalletEntity wallet);
	
}
