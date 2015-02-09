package org.crossv.tests.scenarios.domain;

public class Account {
	private User owner;
	private Double balance;
	private String number;

	public Account(String number, User owner) {
		this(number, owner, 0d);
	}

	public Account(String number, User owner, Double balance) {
		this.number = number;
		this.owner = owner;
		this.balance = balance;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
