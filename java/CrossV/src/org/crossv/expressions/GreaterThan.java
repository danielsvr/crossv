package org.crossv.expressions;

public class GreaterThan extends BooleanExpression {
	public GreaterThan(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " > " + getRight().toString();
	}
}
