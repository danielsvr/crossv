package org.crossv.expressions;

public class Negate extends UnaryExpression {
	public Negate(Expression operand) {
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
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNegate(this);
	}
}
