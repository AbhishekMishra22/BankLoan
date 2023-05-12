package com.terralogic.loan.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.terralogic.loan.model.Customer;

public interface CustomerService {

	public String createAccount(JSONObject jsonCustomer);

	public List<Customer> getAllCustomer();

	public Customer getCustomerByAccountNo(long accountNo);

	public String updateCustomerDetails(long accountNo, Customer c);

	public String removeCustomer(long accountNo);

	public ResponseEntity<Object> searchCustomerByFirstName(String firstName, int pageNo, int pageSize);

	public List<Customer> searchCustomerByLastName(String lastName);

	public Customer searchCustomerByPhoneNumber(String phoneNumber);

	public Customer searchCustomerByMail(String email);

	public Customer searchCustomerByAadharCard(String adhaarCard);

	public Customer searchCustomerByPanCard(String panCard);

	public ResponseEntity<Object> findAll(int paze, int size);

	public ResponseEntity<Object> findByFirstName(int paze, int size, String firstName);

	public Page<Customer> fetchCustomerByproperties(String firstName, String lastName, String email, String phoneNumber,
			String adhaarCard, String panCard, int page, int size);

	public ResponseEntity<Object> getAccountByitsEntry(int page, int size, int limit);

	public List<Customer> getRequiredDetails();

	public Page<Customer> getCustomerBetweenDate(int page, int size, String from, String to);

}
