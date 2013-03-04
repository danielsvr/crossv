package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.*;

public class Add extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Add(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (String.class.isAssignableFrom(leftClass)
				|| String.class.isAssignableFrom(rightClass)) {
			resultClass = String.class;
			return;
		} else if (canPerformNumericPromotionForAll(leftClass, rightClass)) {
			resultClass = getNumericPromotion(leftClass, rightClass);
			return;
		}
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