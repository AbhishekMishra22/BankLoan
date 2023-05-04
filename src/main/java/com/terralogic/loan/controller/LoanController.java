package com.terralogic.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terralogic.loan.model.Loan;
import com.terralogic.loan.service.LoanService;

@RestController
@RequestMapping("/terralogic")
public class LoanController {
	@Autowired
	private LoanService loanService;

	@GetMapping("/loanInfo")
	public String loanInfo(@RequestBody Loan l) {
		return loanService.loanInfo(l);
	}

	@PostMapping("/applyLoan")
	public String applyLoan(@RequestBody Loan l) {
		return loanService.applyLoan(l);
	}

	@GetMapping("/loanDetails")
	public Loan getLoanByAccountNo(@RequestParam long accountNo) {
		return loanService.getLoanByAccountNumber(accountNo);
	}
}
