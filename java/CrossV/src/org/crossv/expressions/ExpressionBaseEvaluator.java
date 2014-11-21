package org.crossv.expressions;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.Iterables;

public class ExpressionBaseEvaluator implements Evaluator {

	private Class<?> instanceClass;
	private Class<?> contextClass;

	public ExpressionBaseEvaluator(org.crossv.expressions.Evaluation expression) {
	}

	@Override
	public Iterable<Evaluation> evaluate(Object obj, Object context) {
		return Iterables.empty();
	}

	@Override
	public Class<?> getInstanceClass() {
		return instanceClass;
	}

	@Override
	public Class<?> getContextClass() {
		return contextClass;
	}

}
