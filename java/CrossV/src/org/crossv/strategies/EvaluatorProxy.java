package org.crossv.strategies;

import java.util.ArrayList;
import java.util.UUID;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.NoContext;
import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.Iterables;

public class EvaluatorProxy implements Evaluator, ObservableEvaluator {
	private final Evaluator realEvaluator;
	private final ArrayList<EvaluatorProxyListener> listeners;
	private Integer contextInheritanceDepth;
	private final UUID batchId;
	private boolean shouldReturnEmptyResults;

	public EvaluatorProxy(Evaluator realEvaluator, UUID batchId) {
		if (realEvaluator == null)
			throw new ArgumentNullException("realEvaluator");
		this.realEvaluator = realEvaluator;
		this.batchId = batchId;
		this.listeners = new ArrayList<EvaluatorProxyListener>();
	}

	@Override
	public Iterable<Evaluation> evaluate(Object obj, Object context) {
		Iterable<Evaluation> result;

		result = realEvaluator.evaluate(obj, context);
		evaluateMethodCalled(result);

		if (shouldReturnEmptyResults)
			return Iterables.empty();

		return result;
	}

	@Override
	public Class<?> getInstanceClass() {
		return realEvaluator.getInstanceClass();
	}

	public UUID getBatchId() {
		return batchId;
	}

	@Override
	public Class<?> getContextClass() {
		Class<?> contextClass;
		contextClass = realEvaluator.getContextClass();
		return contextClass != null ? contextClass : NoContext.class;
	}

	@Override
	public void addListener(EvaluatorProxyListener listener) {
		listeners.add(listener);
	}

	public int getContextDepth() {
		if (contextInheritanceDepth == null) {
			Class<?> clazz = getContextClass();
			int i = 0;
			do {
				clazz = clazz.getSuperclass();
				i++;
			} while (!clazz.equals(Object.class));
			contextInheritanceDepth = i;
		}
		return contextInheritanceDepth;
	}

	@Override
	public void removeListener(EvaluatorProxyListener listener) {
		listeners.remove(listener);
	}

	public void evaluateMethodCalled(Iterable<Evaluation> result) {
		for (EvaluatorProxyListener listener : listeners)
			listener.evaluateMethodCalled(this, result);
	}

	@Override
	public String toString() {
		return super.getClass().getSimpleName() + "# Context = "
				+ getContextClass().getSimpleName();
	}

	public void shouldReturnEmptyResults() {
		shouldReturnEmptyResults = true;
	}
}
