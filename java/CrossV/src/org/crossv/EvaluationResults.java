package org.crossv;

import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;

public class EvaluationResults {
	public static boolean containsAnyError(Iterable<Evaluation> results) {
		return Iterables.any(results, new Predicate<Evaluation>() {
			@Override
			public boolean eval(Evaluation value) {
				return value instanceof EvaluationError;
			}
		});
	}
}
