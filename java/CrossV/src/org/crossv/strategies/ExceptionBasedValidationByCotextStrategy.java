package org.crossv.strategies;

import org.crossv.Evaluator;

public class ExceptionBasedValidationByCotextStrategy extends ValidationStrategy {
	@Override
	public Iterable<? extends Evaluator> apply(
			Iterable<? extends Evaluator> evaluators) {
		return new ExceptionBasedStrategicIterableByContext(evaluators);
	}	
}
