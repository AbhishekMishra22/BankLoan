package com.terralogic.loan.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.terralogic.loan.model.Loan;

public interface LoanRepository extends MongoRepository<Loan, Long> {

	@Query("{'loanAmount': {$gt :?0}}")
	public Optional<Loan> checkLoanExist(long loanAmount);

}
