package com.ing.bank.controller;

import com.ing.bank.entity.CustomerAccount;
import com.ing.bank.entity.Transaction;
import com.ing.bank.model.ActionState;
import com.ing.bank.model.ActionValidator;
import com.ing.bank.model.TransactionType;
import com.ing.bank.service.CustomerAccountService;
import com.ing.bank.service.FunctionsUtilsService;
import com.ing.bank.service.TransactionService;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HM
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerAccountService customerAccountService;

	@Autowired
	private FunctionsUtilsService functionsUtilsService;

	/**
	 * Deposit Transaction.
	 *
	 * @param customerAccountId the Id customer Accout,
	 * @param transactionValue  the transaction value
	 * @return ActionValidator state of the transaction
	 */
	@PostMapping("/deposit/{customerAccountReference}")
	public ActionValidator DepositTransaction(@PathVariable(value = "customerAccountReference") String reference,
			@RequestBody Double transactionValue) {
		CustomerAccount customerAccount = customerAccountService.findCustomerAccountByReference(reference);

		if (customerAccount != null) {
			Transaction transaction = new Transaction(TransactionType.DEPOSIT, transactionValue.doubleValue(), new Date(),
					functionsUtilsService.getAlphaNumericString());
			return transactionService.DepositTransaction(customerAccount, transaction);
		}

		return new ActionValidator(ActionState.BLOQUED, "Account Not Found");
	}

	/**
	 * Withdraw Transaction.
	 *
	 * @param customerAccountId the Id customer Accout,
	 * @param transactionValue  the transaction value
	 * @return ActionValidator state of the transaction
	 */
	@PostMapping("/withdraw/{customerAccountReference}")
	public ActionValidator WithdrawTransaction(@PathVariable(value = "customerAccountReference") String reference,
			@RequestBody double transactionValue) {
		CustomerAccount customerAccount = customerAccountService.findCustomerAccountByReference(reference);

		if (customerAccount != null) {
			Transaction transaction = new Transaction(TransactionType.WITHDRAW, transactionValue, new Date(),
					functionsUtilsService.getAlphaNumericString());
			return transactionService.WithdrawTransaction(customerAccount, transaction);
		}

		return new ActionValidator(ActionState.BLOQUED, "Account Not Found");
	}
}