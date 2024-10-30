package com.recargapay.wallet.infrastructure.adapter.output.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.repository.HistoricalWalletRepository;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.repository.WalletRepository;

@ExtendWith(MockitoExtension.class)
public class WalletPersistenceAdapterTest {

	@InjectMocks
	private WalletPersistenceAdapter walletPersistenceAdapter;

	@Mock
	private WalletRepository repository;

	@Mock
	private HistoricalWalletRepository historicRepository;

	private HistoricalWalletEntity historicalWallet = new HistoricalWalletEntity();
	private List<HistoricalWalletEntity> list = new ArrayList<>();
	private static final String UUID = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc";

	@BeforeEach
	void setUp() {
		historicalWallet.setAmount(new BigDecimal(1));
		list.add(historicalWallet);
	}

	@Test
	void findByAccNumberTest() {
		when(repository.findByAccNumber(UUID)).thenReturn(any(WalletEntity.class));
		assertDoesNotThrow(() -> walletPersistenceAdapter.findByAccNumber(UUID));
	}

	@Test
	void saveWalletEntityTest() {
		assertDoesNotThrow(() -> walletPersistenceAdapter.save(any(WalletEntity.class)));
	}

	@Test
	void saveHistoricalWalletTest() {
		assertDoesNotThrow(() -> walletPersistenceAdapter.save(any(HistoricalWalletEntity.class)));
	}

	@Test
	void findByWalletAccNumberTest() {
		when(historicRepository.findByWalletAccNumber(UUID)).thenReturn(list);
		assertDoesNotThrow(() -> walletPersistenceAdapter.findByWalletAccNumber(UUID));
	}

}
