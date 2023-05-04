package com.terralogic.loan.model;

public class Passbook {
	private long accountNo;

	private String date;
	private String time;
	private long emi;
	private long loanAmount;
	private long balance;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getEmi() {
		return emi;
	}

	public void setEmi(long emi) {
		this.emi = emi;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public Passbook(long accountNo, String date, String time, long emi, long loanAmount, long balance) {
		super();
		this.accountNo = accountNo;
		this.date = date;
		this.time = time;
		this.emi = emi;
		this.loanAmount = loanAmount;
		this.balance = balance;
	}

	public Passbook() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Passbook [accountNo=" + accountNo + ", date=" + date + ", time=" + time + ", emi=" + emi
				+ ", loanAmount=" + loanAmount + ", balance=" + balance + "]";
	}

}
