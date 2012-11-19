package org.crossv.strategies;

import java.util.ArrayList;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public class EvaluatorProxy implements Evaluator, ObservableEvaluator {

	private final Evaluator realEvaluator;
	private final ArrayList<EvaluatorListener> listeners;

	public EvaluatorProxy(Evaluator realEvaluator) {
		this.realEvaluator = realEvaluator;
		this.listeners = new ArrayList<EvaluatorListener>();
	}

	@Override
	public Iterable<Evaluation> evaluate(Object obj, Object context) {
		Iterable<Evaluation> result;

		result = realEvaluator.evaluate(obj, context);
		evaluateMethodCalled(result);

		return result;
	}

	@Override
	public Class<?> getInstanceClass() {
		return realEvaluator.getInstanceClass();
	}

	@Override
	public Class<?> getContextClass() {
		return realEvaluator.getContextClass();
	}

	@Override
	public void addListener(EvaluatorListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(EvaluatorListener listener) {
		listeners.remove(listener);
	}

	public void evaluateMethodCalled(Iterable<Evaluation> result) {
		for (EvaluatorListener listener : listeners)
			listener.evaluateMethodCalled(result);
	}
}
