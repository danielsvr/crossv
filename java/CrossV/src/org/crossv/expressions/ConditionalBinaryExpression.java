package org.crossv.expressions;

import static org.crossv.expressions.ExpressionClass.CBoolean;

public abstract class ConditionalBinaryExpression extends BinaryExpression {

	public ConditionalBinaryExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!left.isAssignableTo(CBoolean) || !right.isAssignableTo(CBoolean))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}
}
