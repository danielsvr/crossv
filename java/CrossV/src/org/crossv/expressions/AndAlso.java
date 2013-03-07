package org.crossv.expressions;

public class AndAlso extends BinaryExpression {
	public AndAlso(Expression left, Expression right) {
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

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAndAlso(this);
	}
}
