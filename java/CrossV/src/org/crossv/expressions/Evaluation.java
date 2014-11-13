package org.crossv.expressions;

import org.crossv.Evaluator;

public class Evaluation extends Expression {

	public Evaluation(Expression scope, Expression[] evaluators) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getResultClass() {
		return Evaluator.class;
	}

}
