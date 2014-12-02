package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CNumber;

public class UnaryMinus extends UnaryExpression {
	public UnaryMinus(Expression operand) {
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
		visitor.visitUnaryMinus(this);
	}
}
