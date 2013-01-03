package org.crossv.expressions;

public class NotEqual extends BooleanExpression {
	public NotEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " != " + getRight().toString();
	}
}
