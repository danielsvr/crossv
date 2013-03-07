package org.crossv.expressions;

public class LeftShift extends BinaryExpression {
	private Class<?> resultClass;

	public LeftShift(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		//@formatter:off
		resultClass = left.isAssignableTo(Long.class) 
				? Long.class
				: Integer.class;
		//@formatter:on
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
		visitor.visitLeftShift(this);
	}
}