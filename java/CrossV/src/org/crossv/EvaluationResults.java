package org.crossv;

import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;

/**
 * Helper class for evaluating evaluation results.
 * 
 * @author yochanan.miykael
 * 
 */
public class EvaluationResults {

	/**
	 * Evaluates if the provided results contains any fault.
	 * 
	 * @param results
	 *            An sequence of evaluations that needs to de checked.
	 * @return {@code true} if the provided results contains any fault.
	 *         {@code false} otherwise.
	 */
	public static boolean containsAnyFault(Iterable<Evaluation> results) {
		return Iterables.any(results, new Predicate<Evaluation>() {
			@Override
			public boolean eval(Evaluation value) {
				return value instanceof EvaluationFault;
			}
		});
	}
}
