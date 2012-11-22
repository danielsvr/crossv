package org.crossv;

/**
 * The class that describes a faulted evaluation result of an {@link Evaluator}.
 * 
 * @author yochanan.miykael
 */
public class EvaluationFault extends Evaluation {

	/**
	 * Creates an instance of {@link EvaluationFault};
	 * 
	 * @param message
	 *            The message that the details will hold.
	 */
	public EvaluationFault(String message) {
		super(createDetails(message));
	}
}
