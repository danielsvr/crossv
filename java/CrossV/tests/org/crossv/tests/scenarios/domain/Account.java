package org.crossv.tests.scenarios.domain;

public class Account {
	private User owner;
	private Double balance;

	public Account(User owner, Double balance) {
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
}
