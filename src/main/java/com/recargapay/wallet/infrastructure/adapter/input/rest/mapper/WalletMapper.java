package com.recargapay.wallet.infrastructure.adapter.input.rest.mapper;

import org.mapstruct.Mapper;

import com.recargapay.wallet.domain.model.Wallet;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

@Mapper(componentModel = "spring")
public interface WalletMapper {

	Wallet toWallet(WalletEntity entity);

}
