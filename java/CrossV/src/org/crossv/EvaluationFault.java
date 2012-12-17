package org.crossv;

import org.crossv.primitives.Strings;

/**
 * The class that describes a faulted evaluation result of an {@link Evaluator}.
 * 
 * @author yochanan.miykael
 */
public class EvaluationFault extends Evaluation {

	private Throwable cause;

	/**
	 * Creates an instance of {@link EvaluationFault};
	 * 
	 * @param message
	 *            The message that the details will hold.
	 */
	public EvaluationFault(String message) {
		super(createDetails(message));
	}

	public EvaluationFault(Throwable cause) {
		super(createDetails(cause.getMessage()));
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}

	@Override
	public String toString() {
		String message;
		String stack = "";

		message = getMessage();
		if (cause != null) {
			message = cause.toString();
			stack = "\n" + Strings.getStacktraceOf(cause);
		}
		return getClass().getSimpleName() + "<" + message + ">" + stack;
	}
}
