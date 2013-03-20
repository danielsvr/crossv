package org.crossv.expressions;

public abstract class ConditionalBinaryExpression extends BinaryExpression {

	public ConditionalBinaryExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!left.isAssignableTo(Boolean.class)
				|| !right.isAssignableTo(Boolean.class))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
