package org.crossv.expressions;

public class AndAlso extends BooleanExpression {

	public AndAlso(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " && " + getRight().toString();
	}
}
