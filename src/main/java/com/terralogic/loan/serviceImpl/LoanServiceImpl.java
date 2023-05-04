package com.terralogic.loan.serviceImpl;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terralogic.loan.Exception.LoanAlreadyExistException;
import com.terralogic.loan.Exception.LoanNotFoundException;
import com.terralogic.loan.model.Loan;
import com.terralogic.loan.repository.LoanRepository;
import com.terralogic.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public String applyLoan(Loan l) {

		Optional<Loan> l1 = loanRepository.findById(l.getAccountNo());

		if (l1.isPresent()) {
			throw new LoanAlreadyExistException("loan already exist on the account " + l.getAccountNo());
		} else {
			loanRepository.save(l);
		}
		JSONObject json = new JSONObject();
		json.put("accountNo", l.getAccountNo());
		json.put("loanStatus", "successfull");

		return json.toString();
	}

	@Override
	public Loan getLoanByAccountNumber(long accountNo) {
		Loan loan = loanRepository.findById(accountNo)
				.orElseThrow(() -> new LoanNotFoundException("Loan not found on this account " + accountNo));

		// TODO Auto-generated method stub
		return loan;
	}

	@Override
	public String loanInfo(Loan l) {
		// TODO Auto-generated method stub

		int emi = l.getLoanAmount() / l.getDuration();

		JSONObject json = new JSONObject();
		json.put("message", "Congratulation you can get loan");
		json.put("emi amount", emi);
		return json.toString();
	}

}
