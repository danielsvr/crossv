package org.crossv.expressions;

public class LessThan extends BooleanExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " < " + getRight().toString();
	}
}
