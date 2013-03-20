package org.crossv.expressions;

public class Complement extends UnaryExpression {
	private Class<?> resultClass;

	public Complement(Expression operand) {
		super(operand);
		verifyOperand();
		if (operand.isAssignableTo(Long.class))
			resultClass = Long.class;
		else
			resultClass = Integer.class;
	}

	private void verifyOperand() {
		if (!operand.isAssignableToAny(Byte.class, Short.class, Integer.class,
				Long.class))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitComplement(this);
	}
}
