package org.crossv.tests.scenarios.users;

public class StoreContext extends BasicContext {
	public boolean containsEmail(String email) {
		return email.equals("already_registered@company.com");
	}
}
