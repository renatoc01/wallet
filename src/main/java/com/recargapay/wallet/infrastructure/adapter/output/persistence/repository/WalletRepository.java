package com.recargapay.wallet.infrastructure.adapter.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {

	WalletEntity findByAccNumber(String accNumber);
	
}
