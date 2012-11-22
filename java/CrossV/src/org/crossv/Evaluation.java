package org.crossv;

/**
 * The base class that describes the result of an {@link Evaluator}. <br/>
 * The build-in results are: {@link EvaluationFault}, {@link EvaluationSuccess}
 * and {@link EvaluationWarning}.
 * 
 * @author yochanan.miykael
 */
public abstract class Evaluation {
	private final EvaluationDetails details;

	/***
	 * Creates an instance of {@link Evaluation} class.
	 */
	protected Evaluation(EvaluationDetails details) {
		this.details = details;
	}

	/**
	 * Gets the message from details.
	 * 
	 * @return the message from details.
	 */
	public String getMessage() {
		return details.getMessage();
	}

	/**
	 * Creates an instance of {@link EvaluationSuccess} that holds the provided
	 * message.
	 * 
	 * @param message
	 *            The message that will be stored as detail.
	 * @return an instance of {@link EvaluationSuccess} that holds the provided
	 *         message.
	 **/
	public static Evaluation success(String message) {
		return new EvaluationSuccess(message);
	}

	/**
	 * Creates an instance of {@link EvaluationWarning} that holds the provided
	 * message.
	 * 
	 * @param message
	 *            The message that will be stored as detail.
	 * @return an instance of {@link EvaluationWarning} that holds the provided
	 *         message.
	 **/
	public static Evaluation warning(String message) {
		return new EvaluationWarning(message);
	}

	/**
	 * Creates an instance of {@link EvaluationFault} that holds the provided
	 * message.
	 * 
	 * @param message
	 *            The message that will be stored as detail.
	 * @return an instance of {@link EvaluationFault} that holds the provided
	 *         message.
	 **/
	public static Evaluation fault(String message) {
		return new EvaluationFault(message);
	}

	/**
	 * Creates an instance of {@link EvaluationDetails} that holds the provided
	 * message.
	 * 
	 * @param message
	 *            The message that will be stored as detail.
	 * @return an instance of {@link EvaluationDetails} that holds the provided
	 *         message.
	 **/
	protected static EvaluationDetails createDetails(String message) {
		return new EvaluationDetails(message);
	}
}
