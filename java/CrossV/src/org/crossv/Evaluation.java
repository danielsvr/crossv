package org.crossv;

public abstract class Evaluation {
	private final String message;

	protected Evaluation(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public static Evaluation success(String message) {
		return new EvaluationSuccess(message);
	}

	public static Evaluation warning(String message) {
		return new EvaluationWarning(message);
	}

	public static Evaluation error(String message) {
		return new EvaluationError(message);
	}
}
