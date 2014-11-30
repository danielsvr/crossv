package org.crossv.expressions;

import org.crossv.Evaluator;
import org.crossv.primitives.IteratorAdapter;

public class ExpressionEvaluatorsIterator extends IteratorAdapter<Evaluator> {

	private Expression[] evaluators;
	private Class<?> objClass;
	private Class<?> contextClass;
	private int currentIndex;
	private ExpressionEvaluationScope evaluationScope;

	public ExpressionEvaluatorsIterator(Class<?> objClass,
			Class<?> contextClass, Expression[] evaluators,
			ExpressionEvaluationScope evaluationScope) {
		this.objClass = objClass;
		this.contextClass = contextClass;
		this.evaluators = evaluators;
		this.evaluationScope = evaluationScope;
		this.currentIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return evaluators.length > 0 && currentIndex < evaluators.length;
	}

	@Override
	public Evaluator next() {
		if (currentIndex >= evaluators.length)
			throw NoSuchElement();

		Expression current = evaluators[currentIndex];
		currentIndex++;

		return new ExpressionBasedEvaluator(objClass, contextClass,
				evaluationScope, current);
	}
}
