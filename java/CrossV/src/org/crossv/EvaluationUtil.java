package org.crossv;

import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;

/**
 * Helper class for evaluating evaluation results.
 * 
 * @author yochanan.miykael
 * 
 */
public class EvaluationUtil {
	private static EvaluationUtil instance = new EvaluationUtil();

	private IsEvaluationFault isFaultEvaluator = new IsEvaluationFault();

	private EvaluationUtil() {
	}

	/**
	 * Evaluates if the provided results contains any fault.
	 * 
	 * @param results
	 *            An sequence of evaluations that needs to be checked.
	 * @return {@code true} if the provided results contains any fault.
	 *         {@code false} otherwise.
	 */
	public static boolean containsAnyFault(Iterable<Evaluation> results) {
		return Iterables.any(results, instance.isFaultEvaluator);
	}

	private class IsEvaluationFault implements Predicate<Evaluation> {
		public boolean eval(Evaluation value) {
			boolean isFault;

			isFault = value instanceof EvaluationFault;
			return isFault;
		}
	}
}
