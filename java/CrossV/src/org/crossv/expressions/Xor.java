package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPerformNumericPromotion;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class Xor extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Xor(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (Boolean.class.isAssignableFrom(leftClass)
				&& Boolean.class.isAssignableFrom(rightClass)) {
			resultClass = Boolean.class;
		} else if (canPerformNumericPromotion(leftClass, rightClass))
			resultClass = getNumericPromotion(leftClass, rightClass);

		if (resultClass == null || !Integer.class.isAssignableFrom(resultClass)
				&& !Long.class.isAssignableFrom(resultClass)
				&& !Boolean.class.isAssignableFrom(resultClass)) {
			throw new IllegalOperandException();
		}
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public String getOperatorString() {
		return "^";
	}
}