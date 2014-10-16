package org.crossv.expressions;

import java.util.Enumeration;

public class SequenceLength extends UnaryExpression {
	private Class<?> resultClass;

	public SequenceLength(Expression operand) {
		super(operand);
		verifyOperand();
		resultClass = Integer.class;
	}

	private void verifyOperand() {
		if (!operand.isAssignableToAny(String.class, Iterable.class,
				Enumeration.class) && !operand.isArray())
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSequenceLength(this);
	}
}
