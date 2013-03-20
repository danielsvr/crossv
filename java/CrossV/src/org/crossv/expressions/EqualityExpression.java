package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;

public abstract class EqualityExpression extends BinaryExpression {

	public EqualityExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!canPromoteNumbers(leftClass, rightClass))
			if (!left.isAssignableTo(right.getResultClass()))
				if (!right.isAssignableTo(left.getResultClass()))
					throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
