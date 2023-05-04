package com.terralogic.loan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection = "Customer")

public class Customer {
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	@Id
	private long accountNo;

	@NotEmpty(message = "firstName can't be empty")
	@NotNull(message = "lastName Can't be null")
	private String firstName;
	@NotEmpty(message = "lastName can't be empty")
	@NotNull(message = "lastName can't be null")
	private String lastName;
	@NotEmpty(message = "phoneNumber can't be Empty")
	@NotNull(message = "phoneNumber can't be null")
	private String phoneNumber;
	@NotEmpty(message = "email can't be empty")
	@NotNull(message = "email can't be null")
	private String email;
	@NotEmpty(message = "please enter your adhaarCardNumber")
	@NotNull(message = "adhaarCard Number can't be null")
	private String adhaarCard;
	@NotEmpty(message = "please enter your panCard number")
	@NotNull(message = "panCard Number can't be null")
	private String panCard;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdhaarCard() {
		return adhaarCard;
	}

	public void setAdhaarCard(String adhaarCard) {
		this.adhaarCard = adhaarCard;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	public Customer(long accountNo,
			@NotEmpty(message = "firstName can't be empty") @NotNull(message = "lastName Can't be null") String firstName,
			@NotEmpty(message = "lastName can't be empty") @NotNull(message = "lastName can't be null") String lastName,
			@NotEmpty(message = "phoneNumber can't be Empty") @NotNull(message = "phoneNumber can't be null") String phoneNumber,
			@NotEmpty(message = "email can't be empty") @NotNull(message = "email can't be null") String email,
			@NotEmpty(message = "please enter your adhaarCardNumber") @NotNull(message = "adhaarCard Number can't be null") String adhaarCard,
			@NotEmpty(message = "please enter your panCard number") @NotNull(message = "panCard Number can't be null") String panCard) {
		super();
		this.accountNo = accountNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.adhaarCard = adhaarCard;
		this.panCard = panCard;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [accountNo=" + accountNo + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", adhaarCard=" + adhaarCard + ", panCard="
				+ panCard + "]";
	}

}
