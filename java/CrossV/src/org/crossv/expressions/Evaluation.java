package org.crossv.expressions;

import org.crossv.Evaluator;

public class Evaluation extends Expression {

	private Expression scope;
	private Expression[] evaluators;

	public Evaluation(Expression scope, Expression[] evaluators) {
		this.scope = scope;
		this.evaluators = evaluators;
	}

	@Override
	public Class<?> getResultClass() {
		return Evaluator.class;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitEvaluation(this);
	}

	public Expression getScope() {
		return scope;
	}

	public Expression[] getEvaluators() {
		return evaluators;
	}
}
