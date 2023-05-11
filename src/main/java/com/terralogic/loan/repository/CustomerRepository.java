package com.terralogic.loan.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.terralogic.loan.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

	@Query("{adhaarCard:?0}")
	public Optional<Customer> getCustomerByAdhaarCard(String adhaarCard);
	
	@Query("{adhaarCard:?0}")
	public Boolean checkCustomerByAdhaarCard(String adhaarCard);

	@Query("{firstName:?0}")
	public Optional<Customer> getCustomerByFirstName(String firstName);

	@Query("{firstName:?0}")
	public Page<Customer> searchCustomerByFirstName(String firstName, Pageable page);

	@Query("{lastName:?0}")
	public Optional<Customer> getCustomerByLastName(String lastName);

	@Query("{lastName:?0}")
	public List<Customer> searchCustomerByLastName(String lastName);

	@Query("{phoneNumber:?0}")
	public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber);

	@Query("{phoneNumber:?0}")
	public Customer searchCustomerByPhoneNumber(String phoneNumber);

	@Query("{email:?0}")
	public Optional<Customer> getCustomerByMail(String email);

	@Query("{email:?0}")
	public Customer searchCustomerByMail(String email);

	@Query("{panCard:?0}")
	public Optional<Customer> getCustomerByPanCard(String panCard);

	@Query("{panCard:?0}")
	public Customer searchCustomerByPanCard(String panCard);

	@Query("{adhaarCard:?0}")
	public Customer searchCustomerByAadharCard(String adhaarCard);

	Page<Customer> findAll(Pageable pageable);

	Page<Customer> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

	@Query("{accountNo:{$lte : ?0}}")
	public Page<Customer> getCustomerByAccountNo(int limit, Pageable page);
	
	@Query(value = "{}",fields = "{firstName : 1,lastName : 1,phoneNumber: 1, email: 1}")
	public List<Customer>getAllRequiredData()	;
	
	@Query("{createdDate:{$gte?0,$lte?1}}")
	public  Page<Customer>getCustomerBetweenFewDates(Date from, Date to, Pageable page);

}
