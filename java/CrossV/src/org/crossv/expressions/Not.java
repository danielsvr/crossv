package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;

public class Not extends UnaryExpression {
	public Not(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isAssignableTo(CBoolean))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNot(this);
	}
}
