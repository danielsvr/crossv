package org.crossv.expressions;

public class LessThanOrEqual extends BooleanExpression {
	public LessThanOrEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " <= " + getRight().toString();
	}
}
