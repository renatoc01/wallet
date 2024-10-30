package com.recargapay.wallet.infrastructure.adapter.input.rest.data.request;

import java.math.BigDecimal;

import com.recargapay.wallet.infrastructure.adapter.input.rest.validation.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundsRequest {

	@Schema(example = "10.00", description = "Amount to deposit or withdraw or transfer")
	private BigDecimal amount;
	@UUID
	@Schema(example = "c0b363d6-2f6b-42d2-9b2d-e3799e6e95fc", description = "Account number of source")
	private String sourceAccNumber;
	@Schema(example = "16a77aa0-b134-4f02-960a-58fcac38d4d9", description = "Account number of destination")
	@UUID
	private String destinationAccNumber;
	
}
