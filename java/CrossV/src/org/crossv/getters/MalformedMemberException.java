package org.crossv.getters;


public class MalformedMemberException extends GetterValidationException {
	private static final long serialVersionUID = -6086468688763148814L;

	public MalformedMemberException(String message) {
		super(message);
	}
}
