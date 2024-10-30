package com.recargapay.wallet.infrastructure.adapter.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;

@Repository
public interface HistoricalWalletRepository extends JpaRepository<HistoricalWalletEntity, Long> {

	List<HistoricalWalletEntity> findByWalletAccNumber(String accNumber);
	
}
