package org.crossv.expressions;

public class InstanceOf extends RelationalExpression {
	public InstanceOf(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (left.returnsPrimitiveType() || !right.isAssignableTo(Class.class))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitInstanceOf(this);
	}

}
