package com.recargapay.wallet.infrastructure.adapter.output.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.recargapay.wallet.infrastructure.adapter.input.rest.util.Action;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.HistoricalWalletEntity;
import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HistoricalWalletRepositoryIT {

	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private HistoricalWalletRepository repository;

	private static final String UUID = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc";

	@Test
	void findByWalletAccNumberTest() {
		var wallet = WalletEntity.builder().id(1L).username("TEST").accNumber(UUID).balance(BigDecimal.ONE).build();
		walletRepository.save(wallet);
		var walletFound = walletRepository.findByAccNumber(UUID);
		
		var historicalWallet = HistoricalWalletEntity.builder().id(1L).date(LocalDateTime.now())
				.action(Action.DEPOSIT.getValue()).amount(BigDecimal.ONE).wallet(walletFound).build();
		repository.save(historicalWallet);

		var historicalWalletFound = repository.findByWalletAccNumber(UUID);
		assertThat(historicalWalletFound).isNotNull();
		assertThat(BigDecimal.ONE).isEqualTo(historicalWalletFound.get(0).getAmount());
	}

}