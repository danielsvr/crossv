package org.crossv.expressions;

public class NotEqual extends BooleanExpression {
	public NotEqual(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right.getResultClass());
	}

	@Override
	public String toString() {
		return "(" + getExpressionString(left) + " != "
				+ getExpressionString(right) + ")";
	}
}
