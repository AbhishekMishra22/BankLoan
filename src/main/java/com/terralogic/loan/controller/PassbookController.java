package com.terralogic.loan.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terralogic.loan.model.Passbook;
import com.terralogic.loan.service.PassbookService;

@RestController
@RequestMapping("/terralogic")

public class PassbookController {
	@Autowired
	private PassbookService passbookService;

	@PostMapping("/transaction")
	public String addLoan(@RequestBody Passbook pass) {
		return passbookService.addLoan(pass);
	}

	@GetMapping("/transaction/accountNo")
	public List<String> getAllTransactionByAccountNo(@RequestParam long accountNo) {
		return passbookService.getAllTransactionByAccountNo(accountNo);
	}

	@PostMapping("/saveLoan")
	public HashMap<Long, Long> saveLoan(@RequestBody Passbook pass) {
		return passbookService.saveLoan(pass);

	}

	@PutMapping("/updateLoanAmount")
	public String payEmi(@RequestParam long accountNo, @RequestBody Passbook pass) {
		return passbookService.payEmi(accountNo, pass);
	}

}
