package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class Modulo extends BinaryExpression {
	private Class<?> resultClass;

	public Modulo(Expression left, Expression right) {
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
	public String getOperatorString() {
		return "%";
	}
}