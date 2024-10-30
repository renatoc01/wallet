package com.recargapay.wallet.infrastructure.adapter.output.exception.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private LocalDateTime date;
	private String message;

}
