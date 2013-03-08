package org.crossv.expressions;

public class EvaluationException extends Exception {
	private static final long serialVersionUID = -2756250182809645557L;

	public EvaluationException(Throwable cause) {
		super(cause.getMessage() + " Please inspect cause for more details.",
				cause);
	}
}
