package org.crossv;

import static org.crossv.primitives.Iterables.any;

import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;

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

	@Override
	public String toString() {
		return getClass().getSimpleName() + "<" + getMessage() + ">";
	}

	protected static EvaluationDetails createDetails(String message){
		return new EvaluationDetails(message);
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
		return Iterables
				.<Evaluation> toIterable(new EvaluationSuccess(message));
	}

	public static Iterable<Evaluation> warning(String message) {
		return Iterables
				.<Evaluation> toIterable(new EvaluationWarning(message));
	}
	
	public static Iterable<Evaluation> warning(Throwable e) {
		return Iterables
				.<Evaluation> toIterable(new EvaluationWarning(e));
	}

	public static Iterable<Evaluation> fault(String message) {
		return Iterables.<Evaluation> toIterable(new EvaluationFault(message));
	}

	public static Iterable<Evaluation> fault(Throwable e) {
		return Iterables.<Evaluation> toIterable(new EvaluationFault(e));
	}
}
