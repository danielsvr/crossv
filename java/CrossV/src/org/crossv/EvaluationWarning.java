package org.crossv;

/**
 * The class that describes an evaluation result of an {@link Evaluator} that is
 * not successful nor faulted.
 * 
 * @author yochanan.miykael
 */
public class EvaluationWarning extends Evaluation {

	/**
	 * Creates an instance of {@link EvaluationWarning};
	 * 
	 * @param message
	 *            The message that the details will hold.
	 */
	public EvaluationWarning(String message) {
		super(createDetails(message));
	}
}
