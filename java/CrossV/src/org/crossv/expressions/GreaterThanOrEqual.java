package org.crossv.expressions;

public class GreaterThanOrEqual extends BooleanExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " >= " + getRight().toString();
	}
}
