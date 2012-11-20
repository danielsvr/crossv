package org.crossv.strategies;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public class ValidationByContextStrategy extends ValidationStrategy implements
		EvaluatorListener {

	@Override
	public void evaluateMethodCalled(Iterable<Evaluation> result) {
	}

	@Override
	protected Iterable<Evaluator> applyStrategyOn(
			Iterable<EvaluatorProxy> evaluators) {
		return new EvaluatorsByContextIterable(this, evaluators);
	}
}
