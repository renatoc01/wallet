package com.recargapay.wallet.infrastructure.adapter.output.persistence;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recargapay.wallet.application.port.output.HistoricalWalletOutputPort;
import com.recargapay.wallet.application.port.output.WalletOutputPort;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.repository.HistoricalWalletRepository;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.repository.WalletRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalletPersistenceAdapter implements WalletOutputPort, HistoricalWalletOutputPort {

	private final WalletRepository repository;
	private final HistoricalWalletRepository historicRepository;
	
	@Override
	public WalletEntity findByAccNumber(String accNumber) {
		return repository.findByAccNumber(accNumber);
	}
	
	@Override
	public WalletEntity save(WalletEntity wallet) {
		return repository.save(wallet);
	}

	@Override
	public HistoricalWalletEntity save(HistoricalWalletEntity historic) {
		return historicRepository.save(historic);
	}

	@Override
	public List<HistoricalWalletEntity> findByWalletAccNumber(String accNumber) {
		return historicRepository.findByWalletAccNumber(accNumber);
	}

}
