package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class Or extends BinaryExpression {
	private Class<?> resultClass;

	public Or(Expression left, Expression right) {
		super(left, right);
		resultClass = calculateResultClass();
		verifyResultClass();
	}

	private void verifyResultClass() {
		if (resultClass == null || !Integer.class.isAssignableFrom(resultClass)
				&& !Long.class.isAssignableFrom(resultClass)
				&& !Boolean.class.isAssignableFrom(resultClass))
			throw illegalOperand();
	}

	private Class<?> calculateResultClass() {
		if (left.isAssignableTo(Boolean.class)
				&& right.isAssignableTo(Boolean.class))
			return Boolean.class;
		else if (canPromoteNumbers(leftClass, rightClass))
			return getNumericPromotion(leftClass, rightClass);
		return null;
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public String getOperatorString() {
		return "|";
	}
}