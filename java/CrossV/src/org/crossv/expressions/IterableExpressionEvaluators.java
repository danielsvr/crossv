package org.crossv.expressions;

import java.util.Iterator;

import org.crossv.Evaluator;

public class IterableExpressionEvaluators implements Iterable<Evaluator> {

	private Class<?> objClass;
	private Class<?> contextClass;
	private Expression[] evaluators;

	public IterableExpressionEvaluators(Class<?> objClass,
			Class<?> contextClass, Expression[] evals) {
		this.objClass = objClass;
		this.contextClass = contextClass;
		this.evaluators = evals;
	}

	@Override
	public Iterator<Evaluator> iterator() {
		return new ExpressionEvaluatorsIterator(objClass, contextClass,
				evaluators);
	}

}
