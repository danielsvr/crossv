package org.crossv.strategies;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public class ValidationByContextStrategy extends ValidationStrategy implements EvaluatorListener {
	@Override
	public Iterable<Evaluator> apply(Iterable<Evaluator> evaluators) {
		return new EvaluatorsByContextIterable(this, evaluators);
	}

	@Override
	public void evaluateMethodCalled(Iterable<Evaluation> result) {
	}
}
