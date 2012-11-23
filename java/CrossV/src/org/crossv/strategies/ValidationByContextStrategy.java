package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.IterableObjects;
import org.crossv.primitives.IteratorCancelationSource;
import org.crossv.primitives.IteratorFactory;

public class ValidationByContextStrategy extends ValidationStrategy {
	@Override
	protected Iterable<Evaluator> applyStrategy(Iterable<EvaluatorProxy> proxies) {

		IteratorCancelationSource cancelationSource;
		EvaluatorsByContextIterator iterator;

		cancelationSource = new IteratorCancelationSource() {
			public boolean isCanceled() {
				return isIterationCanceled();
			}
		};

		return new IterableObjects<Evaluator>(new IteratorFactory() {
			@Override
			public Iterator<Evaluator> create() {
				return new EvaluatorsByContextIterator(proxies,
						cancelationSource);
			}
		});
	}

	@Override
	protected void proxyCreated(EvaluatorProxy proxy) {
		EvaluatorProxyListener listener;

		listener = new EvaluatorProxyListener() {
			public void evaluateMethodCalled(EvaluatorProxy proxy,
					Iterable<Evaluation> result) {
				inspectEvaluatorResults(proxy, result);
			}
		};

		proxy.addListener(listener);
	}

	private boolean isIterationCanceled() {
		return false;
	}

	private void inspectEvaluatorResults(EvaluatorProxy proxy,
			Iterable<Evaluation> result) {
	}
}
