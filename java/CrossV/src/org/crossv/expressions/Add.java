package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.*;

public class Add extends BinaryExpression {
	private Class<?> resultClass;

	public Add(Expression left, Expression right) {
		super(left, right);

		Class<?> leftClass = left.getResultClass();
		Class<?> rightClass = right.getResultClass();

		if (String.class.isAssignableFrom(leftClass)
				|| String.class.isAssignableFrom(rightClass)) {
			resultClass = String.class;
		} else if (canPerformNumericPromotion(leftClass, rightClass)) {
			resultClass = getNumericPromotion(leftClass, rightClass);
		} else
			throw new IllegalOperandException();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public String getOperatorString() {
		return "+";
	}
}