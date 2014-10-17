package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CNumber;

public class Negate extends UnaryExpression {
	public Negate(Expression operand) {
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
		visitor.visitNegate(this);
	}
}
