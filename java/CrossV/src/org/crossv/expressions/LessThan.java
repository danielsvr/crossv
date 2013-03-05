package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;

public class LessThan extends BinaryExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!canPromoteNumbers(leftClass, rightClass))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}

	@Override
	public String getOperatorString() {
		return "<";
	}
}
