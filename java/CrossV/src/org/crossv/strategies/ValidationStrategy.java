package org.crossv.strategies;

import org.crossv.Evaluator;

public abstract class ValidationStrategy {

	public static ValidationStrategy DEFAULT = new ValidationByContextStrategy();

	public abstract Iterable<Evaluator> apply(Iterable<Evaluator> evaluators, Class<?> contextClass);
}
