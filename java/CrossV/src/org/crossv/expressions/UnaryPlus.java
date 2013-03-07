package org.crossv.expressions;

public class UnaryPlus extends UnaryExpression {
	public UnaryPlus(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isAssignableTo(Number.class))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return operand.getResultClass();
	}

	@Override
	public String getOperatorString() {
		return "+";
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitUnaryPlus(this);
	}
}
