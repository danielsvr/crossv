package org.crossv.getters;


public class NoSuchMemberException extends GetterValidationException {
	private static final long serialVersionUID = 2028043419523569648L;

	public NoSuchMemberException(String message) {
		super(message);
	}
}
