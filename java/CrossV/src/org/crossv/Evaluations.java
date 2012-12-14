package org.crossv;

import static org.crossv.primitives.Iterables.*;

import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;

/**
 * Helper class for evaluating evaluation results.
 * 
 * @author yochanan.miykael
 * 
 */
public class Evaluations {
	private Evaluations() {
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
		return any(results, IsEvaluationFault.instance);
	}

	private static class IsEvaluationFault implements Predicate<Evaluation> {
		public static IsEvaluationFault instance = new IsEvaluationFault();

		@Override
		public boolean eval(Evaluation value) {
			boolean isFault;

			isFault = value instanceof EvaluationFault;
			return isFault;
		}
	}


	public static Iterable<Evaluation> success(String message) {
		return Iterables.toIterable(Evaluation.success(message));
	}

	public static Iterable<Evaluation>  warning(String message) {
		return Iterables.toIterable(Evaluation.warning(message));
	}

	public static Iterable<Evaluation> fault(String message) {
		return Iterables.toIterable(Evaluation.fault(message));
	}

	public static Iterable<Evaluation> fault(Throwable e) {
		return Iterables.toIterable(Evaluation.fault(e));
	}
}
