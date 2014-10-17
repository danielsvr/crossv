package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CNumber;

public class UnaryPlus extends UnaryExpression {
	public UnaryPlus(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isAssignableTo(CNumber))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return operand.getResultClass();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitUnaryPlus(this);
	}
}
