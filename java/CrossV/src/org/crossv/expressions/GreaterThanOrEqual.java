package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;

public class GreaterThanOrEqual extends BinaryExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
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
	public void accept(ExpressionVisitor visitor) {
		visitor.visitGreaterThanOrEqual(this);
	}
}
