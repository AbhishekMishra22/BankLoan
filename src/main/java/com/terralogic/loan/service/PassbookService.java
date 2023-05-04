package com.terralogic.loan.service;

import java.util.HashMap;
import java.util.List;

import com.terralogic.loan.model.Passbook;

public interface PassbookService {
	public HashMap<Long, Long> saveLoan(Passbook pass);

	public String payEmi(long accountNo, Passbook pass);

	public String addLoan(Passbook pass);

	public List<String> getAllTransactionByAccountNo(long accountNo);

}
