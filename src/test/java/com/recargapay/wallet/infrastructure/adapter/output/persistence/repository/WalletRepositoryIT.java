package com.recargapay.wallet.infrastructure.adapter.output.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.recargapay.wallet.infrastructure.adapter.output.persistence.entity.WalletEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class WalletRepositoryIT {

	@Autowired
	private WalletRepository repository;

	private static final String UUID = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc";

	@Test
	void findByAccNumberTest() {
		var wallet = WalletEntity.builder().id(1L).username("TEST").accNumber(UUID).balance(BigDecimal.ONE).build();
		repository.save(wallet);

		var walletFound = repository.findByAccNumber(UUID);
		assertThat(walletFound).isNotNull();
		assertThat("TEST").isEqualTo(walletFound.getUsername());
	}

}
