package org.crossv.expressions;

public interface ExpressionEvaluationScope {

	ExpressionEvaluator beginScope(Object instance, Object context);

	void evaluate(Expression expression) throws EvaluationException;

	<E> E getValue() throws EvaluationException;
}
