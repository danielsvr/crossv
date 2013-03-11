package org.crossv.expressions;

class RuntimeEvaluationException extends RuntimeException {
	private static final long serialVersionUID = 6163536626110997376L;

	public RuntimeEvaluationException(Throwable cause) {
		this(cause.getMessage(), cause);
	}

	public RuntimeEvaluationException(String message, Throwable cause) {
		super(message, cause);
	}
}
