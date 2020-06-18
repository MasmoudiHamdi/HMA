package com.ing.bank.controller;

import com.ing.bank.entity.CustomerAccount;
import com.ing.bank.entity.Transaction;
import com.ing.bank.model.ActionState;
import com.ing.bank.model.ActionValidator;
import com.ing.bank.service.CustomerAccountService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HM
 */
@RestController
@RequestMapping("/customerAccount")
public class CustomerAccountController {

	@Autowired
	private CustomerAccountService customerAccountService;

	/**
	 * Gets customerAccountBalance.
	 *
	 * @param customerAccountId
	 * @return the customerAccount Balance
	 */
	@GetMapping("/balance/{customerAccountReference}")
	public ActionValidator getAccountBalance(@PathVariable(value = "customerAccountReference") String reference) {
		CustomerAccount customerAccount = customerAccountService.findCustomerAccountByReference(reference);

		if (customerAccount != null) {
			return new ActionValidator(ActionState.DONE,
					String.valueOf(customerAccountService.displayAccountBalance(customerAccount)));
		}

		return new ActionValidator(ActionState.BLOQUED, "Account Not Found");
	}

	/**
	 * Gets customerAccountTransactions.
	 *
	 * @param customerAccountId
	 * @return the customerAccount Transactions
	 */
	@GetMapping("/transactionhistory/{customerAccountReference}")
	public List<Transaction> getAccountTransactionHistory(
			@PathVariable(value = "customerAccountReference") String reference) {
		CustomerAccount customerAccount = customerAccountService.findCustomerAccountByReference(reference);

		if (customerAccount != null) {
			return customerAccountService.displayAccountTransactionHistory(customerAccount);
		}

		return new ArrayList<>();
	}
}