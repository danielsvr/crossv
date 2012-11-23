package org.crossv.strategies;

import java.util.ArrayList;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.NoContext;

public class EvaluatorProxy implements Evaluator, ObservableEvaluator {
	private final Evaluator realEvaluator;
	private final ArrayList<EvaluatorProxyListener> listeners;
	private Integer contextInheritanceDepth;

	public EvaluatorProxy(Evaluator realEvaluator) {
		this.realEvaluator = realEvaluator;
		this.listeners = new ArrayList<EvaluatorProxyListener>();
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
		return super.toString() + "#" + getContextClass();
	}
}
