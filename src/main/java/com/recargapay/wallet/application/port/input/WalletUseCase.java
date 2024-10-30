package com.recargapay.wallet.application.port.input;

import java.math.BigDecimal;
import java.util.List;

import com.recargapay.wallet.domain.model.HistoricalWallet;
import com.recargapay.wallet.domain.model.Wallet;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.FundsRequest;
import com.recargapay.wallet.infrastructure.adapter.input.rest.data.request.WalletRequest;

public interface WalletUseCase {

	Wallet createWallet(WalletRequest request);

	BigDecimal retrieveBalance(String accNumber);

	List<HistoricalWallet> retrieveHistoricalBalance(String accNumber);

	void depositFunds(FundsRequest request);

	void withdrawFunds(FundsRequest request);

	void transferFunds(FundsRequest request);

}
