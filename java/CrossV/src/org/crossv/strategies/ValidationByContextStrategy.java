package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.IterableObjects;

public class ValidationByContextStrategy extends ValidationStrategy {

	private void inspectEvaluatorResults(EvaluatorProxy proxy,
			Iterable<Evaluation> result) {
	}

	@Override
	protected Iterable<Evaluator> applyStrategy(
			Iterable<EvaluatorProxy> evaluatorProxies) {

		IteratorCancelationSource cancelationSource;
		EvaluatorsByContextIterator iterator;
		Iterator<EvaluatorProxy> proxies;

		cancelationSource = new IteratorCancelationSource() {
			public boolean isCanceled() {
				return isIterationCanceled();
			}
		};

		proxies = evaluatorProxies.iterator();
		iterator = new EvaluatorsByContextIterator(proxies, cancelationSource);
		return new IterableObjects<Evaluator>(iterator);
	}

	private boolean isIterationCanceled() {
		return false;
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
