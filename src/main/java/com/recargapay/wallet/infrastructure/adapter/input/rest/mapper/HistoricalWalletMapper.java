package com.recargapay.wallet.infrastructure.adapter.input.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.recargapay.wallet.domain.model.HistoricalWallet;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;

@Mapper
public interface HistoricalWalletMapper {

	List<HistoricalWallet> map(List<HistoricalWalletEntity> historicalWalletEntity);

	default HistoricalWallet map(HistoricalWalletEntity historicalWalletEntity) {
		HistoricalWallet historicalWallet = new HistoricalWallet();
		historicalWallet.setId(historicalWalletEntity.getId());
		historicalWallet.setDate(historicalWalletEntity.getDate());
		historicalWallet.setAction(historicalWalletEntity.getAction());
		historicalWallet.setAmount(historicalWalletEntity.getAmount());
		return historicalWallet;
	}

}
