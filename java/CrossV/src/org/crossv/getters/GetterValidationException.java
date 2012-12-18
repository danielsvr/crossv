package org.crossv.getters;

public class GetterValidationException extends Exception {
	private static final long serialVersionUID = 7190613955319322991L;

	public GetterValidationException() {
	}

	public GetterValidationException(String message) {
		super(message);
	}
}
