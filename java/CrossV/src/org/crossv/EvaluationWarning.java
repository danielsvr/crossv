package org.crossv;

/**
 * The class that describes an evaluation result of an {@link Evaluator} that is
 * not successful nor faulted.
 * 
 * @author yochanan.miykael
 */
public class EvaluationWarning extends Evaluation {

	private Throwable cause;

	/**
	 * Creates an instance of {@link EvaluationWarning};
	 * 
	 * @param message
	 *            The message that the details will hold.
	 */
	public EvaluationWarning(String message) {
		super(createDetails(message));
	}

	public EvaluationWarning(Throwable cause) {
		super(createDetails(cause.getMessage()));
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}
}
