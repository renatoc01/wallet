package com.recargapay.wallet.infrastructure.adapter.output.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recargapay.wallet.domain.exception.DepositException;
import com.recargapay.wallet.domain.exception.WalletNotFoundException;
import com.recargapay.wallet.infrastructure.adapter.input.rest.util.ErrorMessage;

@ExtendWith(MockitoExtension.class)
public class ExceptionAdapterTest {

	@InjectMocks
	private ExceptionAdapter exceptionAdapter;

	@Test
	void handleConstraintViolationExceptionTest() {
		var execution = exceptionAdapter
				.handleWalletNotFoundException(new WalletNotFoundException(ErrorMessage.WALLET_NOT_FOUND.getValue()));
		assertNotNull(execution);
	}

	@Test
	void handleDepositExceptionExceptionTest() {
		var execution = exceptionAdapter
				.handleDepositExceptionException(new DepositException(ErrorMessage.INVALID_DEPOSIT.getValue()));
		assertNotNull(execution);
	}

}
