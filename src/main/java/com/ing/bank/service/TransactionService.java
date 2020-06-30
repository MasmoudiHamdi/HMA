package com.ing.bank.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.entity.CustomerAccount;
import com.ing.bank.entity.Transaction;
import com.ing.bank.model.ActionState;
import com.ing.bank.model.ActionValidator;
import com.ing.bank.model.TransactionType;
import com.ing.bank.repository.CustomerAccountRepository;

@Service
public class TransactionService {

	@Autowired
	CustomerAccountRepository customerAccountRepository;

	@Autowired
	private FunctionsUtilsService functionsUtilsService;

	public static final BigDecimal MIN_DEPOSIT_VALUE = new BigDecimal(0.01);
	public static final String BLOQUED_DEPOSIT_MESSAGE = "Transaction is not allowed !! deposit limit is 0.01";
	public static final String BLOQUED_WITHDRAW_MESSAGE = "Transaction is not allowed !! not allowed to use overdraft";

	public ActionValidator DepositTransaction(CustomerAccount customerAccount, Transaction transaction) {
		if (MIN_DEPOSIT_VALUE.compareTo(transaction.getTransactionValue()) > 0) {
			return new ActionValidator(ActionState.BLOQUED, BLOQUED_DEPOSIT_MESSAGE);
		}

		List<Transaction> transactions = customerAccount.getTransactions();
		transactions.add(transaction);
		customerAccountRepository.save(customerAccount);
		return new ActionValidator(ActionState.DONE, "");
	}

	public ActionValidator WithdrawTransaction(CustomerAccount customerAccount, Transaction transaction) {
		if (transaction.getTransactionValue().compareTo(customerAccount.getBalance()) > 0) {
			return new ActionValidator(ActionState.BLOQUED, BLOQUED_WITHDRAW_MESSAGE);
		}

		customerAccountRepository.save(updateAccountInformations(customerAccount, transaction));
		return new ActionValidator(ActionState.DONE, "");
	}

	public CustomerAccount updateAccountInformations(CustomerAccount customerAccount, Transaction transaction) {
		List<Transaction> transactions = customerAccount.getTransactions();
		transactions.add(transaction);
		if (transaction.getTransactionType().equals(TransactionType.WITHDRAW)) {
			customerAccount.setBalance(customerAccount.getBalance().subtract(transaction.getTransactionValue()));
		} else {
			customerAccount.setBalance(customerAccount.getBalance().add(transaction.getTransactionValue()));
		}
		return customerAccount;
	}

	public Transaction CreateTransactionObject(BigDecimal transactionValue) {
		return new Transaction(TransactionType.DEPOSIT, transactionValue, LocalDate.now(),
				functionsUtilsService.getAlphaNumericString());
	}
}
