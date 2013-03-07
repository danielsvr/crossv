package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class Subtract extends BinaryExpression {
	private Class<?> resultClass;

	public Subtract(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		resultClass = getNumericPromotion(leftClass, rightClass);
	}

	private void verifyOperands() {
		if (!canPromoteNumbers(leftClass, rightClass))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSubtract(this);
	}
}