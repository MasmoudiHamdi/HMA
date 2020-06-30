package com.ing.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.ing.bank.entity.Customer;
import com.ing.bank.entity.CustomerAccount;
import com.ing.bank.entity.Transaction;
import com.ing.bank.model.ActionValidator;
import com.ing.bank.repository.CustomerRepository;
import com.ing.bank.service.FunctionsUtilsService;
import com.ing.bank.service.TransactionService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BankProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomAccountControllerTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private FunctionsUtilsService functionsUtilsService;

	@LocalServerPort
	private int port;

	List<Transaction> transactions1 = new ArrayList<>();
	List<Transaction> transactions2 = new ArrayList<>();

	String referenceTransaction1;
	String referenceTransaction2;

	String referenceCustomerAccount1;
	String referenceCustomerAccount2;

	String referenceCustomer;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@PostConstruct
	public void init() {
		referenceTransaction1 = functionsUtilsService.getAlphaNumericString();
		referenceTransaction2 = functionsUtilsService.getAlphaNumericString();

		referenceCustomerAccount1 = functionsUtilsService.getAlphaNumericString();
		referenceCustomerAccount2 = functionsUtilsService.getAlphaNumericString();

		referenceCustomer = functionsUtilsService.getAlphaNumericString();

		Transaction transaction1 = transactionService.CreateTransactionObject(new BigDecimal(100.00));
		Transaction transaction2 = transactionService.CreateTransactionObject(new BigDecimal(20.00));

		transactions1.add(transaction1);
		transactions2.add(transaction2);

		CustomerAccount customerAccount1 = new CustomerAccount(new BigDecimal(1200.12), new BigDecimal(500.00),
				referenceCustomerAccount1, transactions1);
		CustomerAccount customerAccount2 = new CustomerAccount(new BigDecimal(20.12), new BigDecimal(100.00),
				referenceCustomerAccount2, transactions2);

		List<CustomerAccount> customerAccounts = new ArrayList<>();
		customerAccounts.add(customerAccount1);
		customerAccounts.add(customerAccount2);

		customerRepository.save(new Customer(referenceCustomer, "Masmoudi", "Hamdi", customerAccounts));
	}

	@Test
	public void contextLoads() {

	}

	// TO DO Add token to http request to connect to the application
	// And excute the test
	// now test failed due to autorization
	@Test
	public void shouldGetAccountBalanceWhenCallCustomerAccountBalance() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ActionValidator> response = restTemplate.exchange(
				getRootUrl() + "/customerAccount/balance/" + referenceCustomerAccount1, HttpMethod.GET, null,
				new ParameterizedTypeReference<ActionValidator>() {
				});

		assertEquals(response.getBody().getMessage(), String.valueOf(new Double(1200.12)));
	}

	@Test
	public void shouldGetAccountTransactionHistoryWhenCallCustomerAccountBalanceTransactionHistory() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// headers.add(headerName, headerValue);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Transaction>> response = restTemplate.exchange(
				getRootUrl() + "/customerAccount/transactionhistory/" + referenceCustomerAccount1, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Transaction>>() {
				});

		assertEquals(response.getBody(), transactions1);
	}
}
