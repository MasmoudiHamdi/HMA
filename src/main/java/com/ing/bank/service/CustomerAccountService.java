package com.ing.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.entity.CustomerAccount;
import com.ing.bank.entity.Transaction;
import com.ing.bank.repository.CustomerAccountRepository;

@Service
public class CustomerAccountService {

	@Autowired
	CustomerAccountRepository customerAccountRepository;

	public BigDecimal displayAccountBalance(CustomerAccount customerAccount) {
		return customerAccount.getBalance();
	}

	public List<Transaction> displayAccountTransactionHistory(CustomerAccount customerAccount) {
		return customerAccount.getTransactions();
	}

	public Optional<CustomerAccount> findCustomerAccountById(Long customerAccountId) {
		return customerAccountRepository.findById(customerAccountId);
	}
	
	public CustomerAccount findCustomerAccountByReference(String reference) {
		return customerAccountRepository.findByReference(reference);
	}
}
