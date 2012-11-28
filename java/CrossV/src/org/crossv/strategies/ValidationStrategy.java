package org.crossv.strategies;

import org.crossv.Evaluator;

public abstract class ValidationStrategy {

	public static ValidationStrategy DEFAULT = new ValidationByCotextStrategy();

	protected ValidationStrategy() {
	}

	public abstract Iterable<? extends Evaluator> apply(Iterable<? extends Evaluator> evaluators);
}
