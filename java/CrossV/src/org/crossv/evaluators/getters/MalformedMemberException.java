package org.crossv.evaluators.getters;

public class MalformedMemberException extends ReflectiveOperationException {
	private static final long serialVersionUID = -6086468688763148814L;

	public MalformedMemberException(String message) {
		super(message);
	}
}
