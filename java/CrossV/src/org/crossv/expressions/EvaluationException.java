package org.crossv.expressions;

public class EvaluationException extends Exception {
	private static final long serialVersionUID = -2756250182809645557L;

	public EvaluationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EvaluationException(String string) {
		super(string);
	}
}
