package com.recargapay.wallet.infrastructure.adapter.output.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "historical_wallet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricalWalletEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime date;
	private String action;
	private BigDecimal amount;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="wallet_id", nullable=false)
	private WalletEntity wallet;
	
	public HistoricalWalletEntity save(WalletEntity wallet, String action, BigDecimal amount) {
		return HistoricalWalletEntity.builder().date(LocalDateTime.now()).action(action).wallet(wallet).amount(amount).build();
	}
	
}
