package com.recargapay.wallet.domain.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

	@Schema(example = "1", description = "Id of wallet")
	private Long id;
	@Schema(example = "Renato", description = "Username")
	private String username;
	@Schema(example = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc", description = "Account number of source")
	private String accNumber;
	@Schema(example = "10.00", description = "Balance of wallet")
	private BigDecimal balance;

}
