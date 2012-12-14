package org.crossv.evaluators.getters;

public class NoSuchMemberException extends ReflectiveOperationException {
	private static final long serialVersionUID = 2028043419523569648L;

	public NoSuchMemberException(String message) {
		super(message);
	}
}
