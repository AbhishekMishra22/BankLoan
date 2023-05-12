package com.terralogic.loan.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.terralogic.loan.Exception.CustomerAlreadyExistException;
import com.terralogic.loan.Exception.CustomerNotFoundException;
import com.terralogic.loan.kafka.KafkaConsumerService;
import com.terralogic.loan.kafka.KafkaProducerService;
import com.terralogic.loan.model.Customer;
import com.terralogic.loan.repository.CustomerRepository;
import com.terralogic.loan.service.CustomerService;
import com.terralogic.loan.service.DbSequenceService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	public KafkaProducerService kafkaProducerService;

	@Autowired
	private DbSequenceService dbSequenceService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@Override
	public String createAccount(JSONObject jsonCustomer) {

		Query query = new Query();
		query.addCriteria(Criteria.where("adhaarCard").is(jsonCustomer.get("adhaarCard")));
		List<Customer> users = mongoTemplate.find(query, Customer.class);

		if (!users.isEmpty()) {
			throw new CustomerAlreadyExistException("Account already exist with this aadhar card");
		}

		else {

			long accNo = dbSequenceService.generateDbSequence(Customer.SEQUENCE_NAME);
			jsonCustomer.put("accountNo", accNo);

			Customer customer = new Customer();

			customer.setAccountNo(jsonCustomer.getLong("accountNo"));
			customer.setFirstName(jsonCustomer.getString("firstName"));
			customer.setLastName(jsonCustomer.getString("lastName"));
			customer.setEmail(jsonCustomer.getString("email"));
			customer.setPhoneNumber(jsonCustomer.getString("phoneNumber"));
			customer.setAdhaarCard(jsonCustomer.getString("adhaarCard"));
			customer.setPanCard(jsonCustomer.getString("panCard"));
			customer.setCreatedDate(LocalDate.now());
			customerRepository.save(customer);
			String message = jsonCustomer.toString();
			kafkaProducerService.sendMessage("${spring.kafka.topic.name}", message);

		}

		JSONObject json = new JSONObject();
		json.put("Status", "Successfull");
		json.put("accountNo", jsonCustomer.getLong("accountNo"));
		json.put("Message", "Your Account got created Successfully");
		String messages = json.toString();
		kafkaProducerService.sendResponse("${spring.kafka.topic1.name}", messages);
		return json.toString();

	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();

	}

	@Override
	public Customer getCustomerByAccountNo(long accountNo) {
		Customer customer = customerRepository.findById(accountNo).orElseThrow(
				() -> new CustomerNotFoundException("Customer not found with account number : " + accountNo));

		return customer;

	}

	@Override
	public String updateCustomerDetails(long accountNo, Customer c) {

		Customer customer = customerRepository.findById(accountNo).orElseThrow(
				() -> new CustomerNotFoundException("Customer not found with account number : " + accountNo));

		customer.setFirstName(c.getFirstName());
		customer.setLastName(c.getLastName());
		customer.setEmail(c.getEmail());
		customer.setPhoneNumber(c.getPhoneNumber());
		customer.setAdhaarCard(c.getAdhaarCard());
		customer.setPanCard(c.getPanCard());
		customerRepository.save(customer);

		JSONObject json = new JSONObject();
		json.put("accountNo", accountNo);

		json.put("updateStatus", "success");

		return json.toString();
	}

	@Override
	public String removeCustomer(long accountNo) {

		Customer customer = customerRepository.findById(accountNo).orElseThrow(
				() -> new CustomerNotFoundException("Customer not found with account number : " + accountNo));
		customerRepository.delete(customer);
		JSONObject json = new JSONObject();
		json.put("accountNo", accountNo);
		json.put("deleteStatus", "Success");
		return json.toString();
	}

	@Override
	public ResponseEntity<Object> searchCustomerByFirstName(String firstName, int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);

		Query query = new Query().with(paging);
		query.addCriteria(Criteria.where("firstName").regex("^A"));

		Page<Customer> pageTuts = PageableExecutionUtils.getPage(mongoTemplate.find(query, Customer.class), paging,
				() -> mongoTemplate.count(query, Customer.class));

		List<Customer> list = new ArrayList<Customer>();
		list = pageTuts.getContent();

		if (list.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		JSONObject json = new JSONObject();
		json.put("page", list);
		json.put("currentPage", pageTuts.getNumber());
		json.put("totalItems", pageTuts.getTotalElements());
		json.put("totalPages", pageTuts.getTotalPages());

		return new ResponseEntity<Object>(json, HttpStatus.OK);

	}

	@Override
	public List<Customer> searchCustomerByLastName(String lastName) {

		customerRepository.getCustomerByLastName(lastName)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found with last name " + lastName));
		return customerRepository.searchCustomerByLastName(lastName);
	}

	@Override
	public Customer searchCustomerByPhoneNumber(String phoneNumber) {
		customerRepository.getCustomerByPhoneNumber(phoneNumber).orElseThrow(
				() -> new CustomerNotFoundException("Customer Not found with phone number " + phoneNumber));
		return customerRepository.searchCustomerByPhoneNumber(phoneNumber);

	}

	@Override
	public Customer searchCustomerByMail(String email) {
		customerRepository.getCustomerByMail(email)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found with mail " + email));
		return customerRepository.searchCustomerByMail(email);

	}

	@Override
	public Customer searchCustomerByAadharCard(String adhaarCard) {

		customerRepository.getCustomerByAdhaarCard(adhaarCard)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found with Aadhar Card " + adhaarCard));
		return customerRepository.searchCustomerByAadharCard(adhaarCard);

	}

	@Override
	public Customer searchCustomerByPanCard(String panCard) {
		customerRepository.getCustomerByPanCard(panCard)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found with Pan Card " + panCard));
		return customerRepository.searchCustomerByPanCard(panCard);

	}

	@Override
	public ResponseEntity<Object> findAll(int paze, int size) {

		List<Customer> customer = new ArrayList<Customer>();
		Pageable paging = PageRequest.of(paze, size);

		Page<Customer> pageTuts = customerRepository.findAll(paging);
		customer = pageTuts.getContent();
		if (customer.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		JSONObject json = new JSONObject();
		json.put("page", customer);
		json.put("currentPage", pageTuts.getNumber());
		json.put("totalItems", pageTuts.getTotalElements());
		json.put("totalPages", pageTuts.getTotalPages());

		return new ResponseEntity<Object>(json.toString(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByFirstName(int paze, int size, String firstName) {

		List<Customer> list = new ArrayList<Customer>();
		Pageable paging = PageRequest.of(paze, size);
		Page<Customer> pageTuts = customerRepository.findByFirstNameContainingIgnoreCase(firstName, paging);
		list = pageTuts.getContent();
		if (list.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		JSONObject json = new JSONObject();
		json.put("page", list);
		json.put("currentPage", pageTuts.getNumber());
		json.put("totalItems", pageTuts.getTotalElements());
		json.put("totalPages", pageTuts.getTotalPages());

		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}

	@Override
	public Page<Customer> fetchCustomerByproperties(String firstName, String lastName, String email, String phoneNumber,
			String adhaarCard, String panCard, int page, int size) {

		Pageable paging = PageRequest.of(page, size);
		Query query = new Query().with(paging);
		final List<Criteria> criteria = new ArrayList<>();
		if (firstName != null && !firstName.isEmpty())
			criteria.add(Criteria.where("firstName").is(firstName));
		if (lastName != null && !lastName.isEmpty())
			criteria.add(Criteria.where("lastName").is(lastName));
		if (email != null && !email.isEmpty())
			criteria.add(Criteria.where("email").is(email));
		if (phoneNumber != null && !phoneNumber.isEmpty())
			criteria.add(Criteria.where("phoneNumber").is(phoneNumber));
		if (adhaarCard != null && !adhaarCard.isEmpty())
			criteria.add(Criteria.where("adhaarCard").is(adhaarCard));
		if (panCard != null && !panCard.isEmpty())
			criteria.add(Criteria.where("panCard").is(panCard));
		if (!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		return PageableExecutionUtils.getPage(mongoTemplate.find(query, Customer.class), paging,
				() -> mongoTemplate.count(query, Customer.class));

	}

	@Override
	public ResponseEntity<Object> getAccountByitsEntry(int page, int size, int limit) {

		Pageable paging = PageRequest.of(page, size);
		Page<Customer> pageTuts = customerRepository.getCustomerByAccountNo(limit, paging);
		List<Customer> list = new ArrayList<>();
		list = pageTuts.getContent();

		logger.info(String.format("Message received -> %s", list.toString()));

		if (list.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		JSONObject json = new JSONObject();
		json.put("page", list);
		json.put("currentPage", pageTuts.getNumber());
		json.put("totalItems", pageTuts.getTotalElements());
		json.put("totalPages", pageTuts.getTotalPages());

		return new ResponseEntity<Object>(json.toString(), HttpStatus.OK);
	}

	@Override
	public List<Customer> getRequiredDetails() {

		List<Customer> list = customerRepository.getAllRequiredData();
		System.out.println(list);
		return list;

	}

	@Override
	public Page<Customer> getCustomerBetweenDate(int page, int size, String from, String to) {

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date froms = null;

		try {
			froms = formatter1.parse(from);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Date tos = null;

		try {
			tos = formatter1.parse(from);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (froms != null && tos != null) {

			Pageable paging = PageRequest.of(page, size);
			Query query = new Query().with(paging);
			final List<Criteria> criteria = new ArrayList<>();
			criteria.add(Criteria.where("createdDate").gte(froms).lte(to));

			return PageableExecutionUtils.getPage(mongoTemplate.find(query, Customer.class), paging,
					() -> mongoTemplate.count(query, Customer.class));

		}

		else
			return null;

	}
}
