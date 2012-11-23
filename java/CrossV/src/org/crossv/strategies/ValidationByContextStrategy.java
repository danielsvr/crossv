package org.crossv.strategies;

import static org.crossv.EvaluationUtil.containsAnyFault;
import static org.crossv.primitives.Iterables.addAllToList;

import java.util.ArrayList;
import java.util.List;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.IterableObjects;
import org.crossv.primitives.IteratorCancelationSource;

public class ValidationByContextStrategy extends ValidationStrategy {
	IteratorCancelationSource cancelationSource;
	EvaluatorProxyListener listener;
	List<Evaluation> evaluations;
	int currentEvaluationDepth;
	boolean isIterationCanceled;

	public ValidationByContextStrategy() {
		cancelationSource = new IteratorCancelationSource() {
			public boolean isCanceled() {
				return isIterationCanceled();
			}
		};

		listener = new EvaluatorProxyListener() {
			public void evaluateMethodCalled(EvaluatorProxy proxy,
					Iterable<Evaluation> result) {
				inspectEvaluatorResults(proxy, result);
			}
		};
	}

	@Override
	protected Iterable<Evaluator> applyStrategy(Iterable<EvaluatorProxy> proxies) {
		EvaluatorsByContextIterator iterator;

		evaluations = new ArrayList<Evaluation>();
		currentEvaluationDepth = 0;
		isIterationCanceled = false;
		iterator = new EvaluatorsByContextIterator(proxies, cancelationSource);
		return new IterableObjects<Evaluator>(iterator);
	}

	@Override
	protected void proxyCreated(EvaluatorProxy proxy) {
		proxy.addListener(listener);
	}

	private boolean isIterationCanceled() {
		return isIterationCanceled;
	}

	private void inspectEvaluatorResults(EvaluatorProxy proxy,
			Iterable<Evaluation> result) {
		if (!proxy.getEvaluationBatchId().equals(getCurrentBatchId()))
			return;

		addAllToList(evaluations, result);

		if (proxy.getContextDepth() == currentEvaluationDepth)
			return;

		isIterationCanceled = containsAnyFault(evaluations);
		currentEvaluationDepth++;
	}
}
