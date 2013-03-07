package org.crossv.expressions;

public class RightShift extends BinaryExpression {
	private Class<?> resultClass;

	public RightShift(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		resultClass = left.isAssignableTo(Long.class) ? Long.class
				: Integer.class;
	}

	private void verifyOperands() {
		if (left.isAssignableToAny(Double.class, Float.class)
				|| !left.isAssignableTo(Number.class)
				|| right.isAssignableToAny(Double.class, Float.class)
				|| !right.isAssignableTo(Number.class))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitRightShift(this);
	}
}