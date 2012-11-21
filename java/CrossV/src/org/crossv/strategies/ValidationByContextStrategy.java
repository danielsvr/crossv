package org.crossv.strategies;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public class ValidationByContextStrategy extends ValidationStrategy {

	private void inspectEvaluatorResults(EvaluatorProxy proxy, Iterable<Evaluation> result) {
	}

	@Override
	protected Iterable<Evaluator> applyStrategy(
			Iterable<EvaluatorProxy> evaluators) {
		return new EvaluatorsByContextIterable(evaluators);
	}

	@Override
	protected void proxyCreated(EvaluatorProxy proxy) {
		proxy.addListener(new EvaluatorProxyListener() {
			public void evaluateMethodCalled(EvaluatorProxy proxy,
					Iterable<Evaluation> result) {
				inspectEvaluatorResults(proxy, result);
			}
		});
	}
}
