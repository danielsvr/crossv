package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPerformNumericPromotion;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class And extends BinaryExpression {
	private Class<?> resultClass;

	public And(Expression left, Expression right) {
		super(left, right);

		Class<?> leftClass = left.getResultClass();
		Class<?> rightClass = right.getResultClass();

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
		return "&";
	}
}