package org.crossv.expressions;

public class Not extends UnaryExpression {
	public Not(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isAssignableTo(Boolean.class))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}

	@Override
	public String getOperatorString() {
		return "!";
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNot(this);
	}
}
