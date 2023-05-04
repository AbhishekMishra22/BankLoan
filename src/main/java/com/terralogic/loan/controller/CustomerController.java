package com.terralogic.loan.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.terralogic.loan.model.Customer;
import com.terralogic.loan.service.CustomerService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/terralogic")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

//	@PostMapping("/customer")
//	@ResponseBody
//	private ResponseEntity<String> createCustomer(@Valid @RequestBody Customer customer) {
//
//		return new ResponseEntity<String>(customerService.createAccount(customer), HttpStatus.CREATED);
//
//	}
	@PostMapping("/customer")
	@ResponseBody
	private ResponseEntity<String> createCustomer1(@Valid @RequestBody String jsonCustomer) {

		JSONObject json = new JSONObject(jsonCustomer);

		return new ResponseEntity<String>(customerService.createAccount(json), HttpStatus.CREATED);

	}

	@GetMapping("/customer")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();

	}

	@GetMapping("/customer/accountNo")
	private ResponseEntity<Customer> getCustomerByAccountNo(@RequestParam long accountNo) {
		return new ResponseEntity<Customer>(customerService.getCustomerByAccountNo(accountNo), HttpStatus.FOUND);
	}

	@PutMapping("/customer")
	private ResponseEntity<String> updateCustomerDetails(@Valid @RequestParam long accountNo, @RequestBody Customer c) {
		return new ResponseEntity<String>(customerService.updateCustomerDetails(accountNo, c), HttpStatus.OK);

	}

	@DeleteMapping("/customer/accountNo")
	private ResponseEntity<String> removeCustomer(@RequestParam long accountNo) {
		return new ResponseEntity<String>(customerService.removeCustomer(accountNo), HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer/firstNameRegax")
	private ResponseEntity<Object> searchCustomerByFirstName(@RequestParam String firstName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
		return customerService.searchCustomerByFirstName(firstName, page, size);
	}

	@GetMapping("/customer/lastName")
	private List<Customer> searchCustomerByLastName(@RequestParam String lastName) {
		return customerService.searchCustomerByLastName(lastName);
	}

	@GetMapping("/customer/phoneNumber")
	private ResponseEntity<Customer> searchCustomerByPhoneNumber(@RequestParam String phoneNumber) {
		return new ResponseEntity<Customer>(customerService.searchCustomerByPhoneNumber(phoneNumber), HttpStatus.FOUND);
	}

	@GetMapping("/customer/email")
	private ResponseEntity<Customer> searchCustomerByMail(@RequestParam String email) {
		return new ResponseEntity<Customer>(customerService.searchCustomerByMail(email), HttpStatus.FOUND);
	}

	@GetMapping("/customer/panCard")
	private ResponseEntity<Customer> searchCustomerByPanCard(@RequestParam String panCard) {
		return new ResponseEntity<Customer>(customerService.searchCustomerByPanCard(panCard), HttpStatus.FOUND);
	}

	@GetMapping("/customer/aadharCard")
	private ResponseEntity<Customer> getCustomerByAadharCard(@RequestParam String adhaarCard) {
		return new ResponseEntity<Customer>(customerService.searchCustomerByAadharCard(adhaarCard), HttpStatus.FOUND);
	}

	@GetMapping("/customer/pagination")
	private ResponseEntity<Object> findAllPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "0") int size) {
		return new ResponseEntity<Object>(customerService.findAll(page, size), HttpStatus.OK);

	}

	@GetMapping("/customer/paginationname")
	private ResponseEntity<Object> getCustomerByFirstName(@RequestParam String firstName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
		return new ResponseEntity<Object>(customerService.findByFirstName(page, size, firstName), HttpStatus.OK);
	}

	@GetMapping("/customer/filter")
	private ResponseEntity<Page<Customer>> getAll(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String email,
			@RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String adhaarCard,
			@RequestParam(required = false) String panacard, @RequestParam int page, @RequestParam int size) {

		return new ResponseEntity<Page<Customer>>(customerService.fetchCustomerByproperties(firstName, lastName, email,
				phoneNumber, adhaarCard, panacard, page, size), HttpStatus.OK);
	}

	@GetMapping("/customer/lessThan")
	private ResponseEntity<Object> getAccountByitsEntry(@RequestParam int limit,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
		return new ResponseEntity<Object>(customerService.getAccountByitsEntry(page, size, limit), HttpStatus.OK);

	}
	
	@GetMapping("/customer/requiredDetails")
	public List<Customer>getRequiredDetails()
	{
		return customerService.getRequiredDetails();
	}
}
