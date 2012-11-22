package org.crossv;

/**
 * The class that describes a successful evaluation result of an
 * {@link Evaluator}.
 * 
 * @author yochanan.miykael
 */
public class EvaluationSuccess extends Evaluation {

	/**
	 * Creates an instance of {@link EvaluationSuccess};
	 * 
	 * @param message
	 *            The message that the details will hold.
	 */
	public EvaluationSuccess(String message) {
		super(createDetails(message));
	}

}
