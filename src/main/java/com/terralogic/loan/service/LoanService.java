package com.terralogic.loan.service;

import com.terralogic.loan.model.Loan;

public interface LoanService {
	public String loanInfo(Loan l);

	public String applyLoan(Loan l);

	public Loan getLoanByAccountNumber(long accountNo);

}
