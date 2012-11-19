package org.crossv.strategies;

import org.crossv.Evaluator;

public class ValidationByContextStrategy extends ValidationStrategy {

	@Override
	public Iterable<Evaluator> apply(Iterable<Evaluator> evaluators,
			Class<?> contextClass) {
		return new EvaluatorsByContextIterable(evaluators, contextClass);
	}

}
