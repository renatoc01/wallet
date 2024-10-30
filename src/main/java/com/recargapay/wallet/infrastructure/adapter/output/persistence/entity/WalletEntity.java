package com.recargapay.wallet.infrastructure.adapter.output.persistence.entity;

import java.math.BigDecimal;
import java.util.List;
import static java.util.Objects.*;

import com.recargapay.wallet.domain.exception.DepositException;
import com.recargapay.wallet.infrastructure.adapter.input.rest.util.ErrorMessage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "wallet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String accNumber;
	private BigDecimal balance;
	@OneToMany(mappedBy="wallet")
	private List<HistoricalWalletEntity> items;

	public void deposit(BigDecimal amount) {
		if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new DepositException(ErrorMessage.INVALID_DEPOSIT.getValue());
		}
		this.balance = this.balance.add(amount);
	}

	public void withdraw(BigDecimal amount) {
		if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new DepositException(ErrorMessage.INVALID_DEPOSIT.getValue());
		}
		if (amount.compareTo(this.balance) > 0) {
			throw new DepositException(ErrorMessage.INSUFFICIENT_BALANCE.getValue());
		}
		this.balance = this.balance.subtract(amount);
	}

}
