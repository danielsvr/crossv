package org.crossv.expressions;

public class InstanceOf extends BooleanExpression {

	public InstanceOf(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String toString() {
		return "(" + getExpressionString(left) + " instanceof "
				+ getExpressionString(right) + ")";
	}
}
