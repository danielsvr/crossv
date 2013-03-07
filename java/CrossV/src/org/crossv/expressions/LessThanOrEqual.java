package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;

public class LessThanOrEqual extends BinaryExpression {
	public LessThanOrEqual(Expression left, Expression right) {
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
		visitor.visitLessThanOrEqual(this);
	}
}
