package org.crossv.expressions;

public class Equal extends BooleanExpression {
	public Equal(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " == " + getRight().toString();
	}
}
