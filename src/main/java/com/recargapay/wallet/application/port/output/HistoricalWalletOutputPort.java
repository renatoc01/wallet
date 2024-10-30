package com.recargapay.wallet.application.port.output;

import java.util.List;

import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;

public interface HistoricalWalletOutputPort {

	HistoricalWalletEntity save(HistoricalWalletEntity historic);
	List<HistoricalWalletEntity> findByWalletAccNumber(String accNumber);

}
