package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPerformNumericPromotionForAll;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class Modulo extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Modulo(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (!canPerformNumericPromotionForAll(leftClass, rightClass))
			throw new IllegalOperandException();
		resultClass = getNumericPromotion(leftClass, rightClass);
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