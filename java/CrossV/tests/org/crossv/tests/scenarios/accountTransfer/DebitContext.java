package org.crossv.tests.scenarios.accountTransfer;

public class DebitContext extends BasicContext {
	private double balanceTreshold;
	private double debit;

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getBalanceTreshold() {
		return balanceTreshold;
	}

	public void setBalanceTreshold(double balanceTreshold) {
		this.balanceTreshold = balanceTreshold;
	}
}
