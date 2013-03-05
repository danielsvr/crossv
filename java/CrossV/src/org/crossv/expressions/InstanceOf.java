package org.crossv.expressions;

public class InstanceOf extends BinaryExpression {
	public InstanceOf(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		boolean isLeftPrimitive = left.returnsPrimitiveType();
		boolean isRightClass = right.isAssignableTo(Class.class);

		if (isLeftPrimitive && !isRightClass)
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}

	@Override
	public String getOperatorString() {
		return "instanceof";
	}
}
