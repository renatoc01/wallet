package com.recargapay.wallet.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricalWallet {

	@Schema(example = "1", description = "Id of historical wallet")
	private Long id;
	@Schema(example = "10-30-2024", description = "Date of action")
	private LocalDateTime date;
	@Schema(example = "Deposit, Withdraw, Transfer", description = "Action to deposit or withdraw or transfer")
	private String action;
	@Schema(example = "10.00", description = "Amount to deposit or withdraw or transfer")
	private BigDecimal amount;
	
}
