package org.crossv.tests.scenarios.users;

import java.util.regex.Pattern;

public class HelperContext {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";

	private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
	private Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

	public boolean validatesEmailPattern(String email) {
		return emailPattern.matcher(email).matches();
	}

	public boolean meetsMinimalComplexity(String password) {
		return passwordPattern.matcher(password).matches();
	}

}
