package com.recargapay.wallet.infrastructure.adapter.input.rest.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {

	@Schema(example = "Renato", description = "Username")
	@NotBlank
	private String username;

}
