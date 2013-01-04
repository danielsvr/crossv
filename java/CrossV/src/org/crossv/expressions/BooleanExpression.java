package org.crossv.expressions;

public abstract class BooleanExpression extends BinaryExpression {
	public BooleanExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
