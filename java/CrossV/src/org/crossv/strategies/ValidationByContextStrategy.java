package org.crossv.strategies;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.Iterables;

public class ValidationByContextStrategy extends ValidationStrategy {

	private void inspectEvaluatorResults(Iterable<Evaluation> result) {
	}

	@Override
	protected Iterable<Evaluator> applyStrategy(
			Iterable<EvaluatorProxy> evaluators) {
		// return new EvaluatorsByContextIterable(evaluators);
		return Iterables.<EvaluatorProxy, Evaluator> cast(evaluators);
	}

	@Override
	protected void proxyCreated(EvaluatorProxy proxy) {
		proxy.addListener(new EvaluatorListener() {
			public void evaluateMethodCalled(Iterable<Evaluation> result) {
				inspectEvaluatorResults(result);
			}
		});
	}
}
