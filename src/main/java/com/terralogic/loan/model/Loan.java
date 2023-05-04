package com.terralogic.loan.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loan")
public class Loan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long accountNo;

	private int loanAmount;

	private int duration;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Loan(long accountNo, int loanAmount, int duration) {
		super();
		this.accountNo = accountNo;
		this.loanAmount = loanAmount;
		this.duration = duration;
	}

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Loan [accountNo=" + accountNo + ", loanAmount=" + loanAmount + ", duration=" + duration + "]";
	}

}
