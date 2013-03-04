package org.crossv.expressions;

public class UnaryPlus extends UnaryExpression {
	public UnaryPlus(Expression operand) {
		super(operand);
		checkIfNumeric(operand);
	}

	private void checkIfNumeric(Expression operand) {
		Class<?> resultClass = operand.getResultClass();
		if (resultClass.equals(Byte.class) || resultClass.equals(Short.class)
				|| resultClass.equals(Integer.class)
				|| resultClass.equals(Long.class)
				|| resultClass.equals(Float.class)
				|| resultClass.equals(Double.class))
			return;

		throw new IllegalOperandException();
	}

	@Override
	public Class<?> getResultClass() {
		return operand.getResultClass();
	}

	@Override
	public String getOperatorString() {
		return "+";
	}
}
