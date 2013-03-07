package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

public class And extends BinaryExpression {
	private Class<?> resultClass;

	public And(Expression left, Expression right) {
		super(left, right);
		resultClass = calculateResultClass(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (resultClass == null || !Boolean.class.isAssignableFrom(resultClass)
				&& !Integer.class.isAssignableFrom(resultClass)
				&& !Long.class.isAssignableFrom(resultClass))
			throw illegalOperand();
	}

	private Class<?> calculateResultClass(Expression left, Expression right) {
		if (canPromoteNumbers(leftClass, rightClass))
			return getNumericPromotion(leftClass, rightClass);
		else if (left.isAssignableTo(Boolean.class)
				&& right.isAssignableTo(Boolean.class)) {
			return Boolean.class;
		}
		return null;
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAnd(this);
	}
}