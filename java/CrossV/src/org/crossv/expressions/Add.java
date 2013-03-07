package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.*;

public class Add extends BinaryExpression {
	private Class<?> resultClass;

	public Add(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		resultClass = calculateResultClass();
	}

	private Class<?> calculateResultClass() {
		if (canPromoteNumbers(leftClass, rightClass))
			return getNumericPromotion(leftClass, rightClass);
		return String.class;
	}

	private void verifyOperands() {
		if (!left.isAssignableTo(String.class)
				&& !right.isAssignableTo(String.class)
				&& !canPromoteNumbers(leftClass, rightClass))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAdd(this);
	}
}