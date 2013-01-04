package org.crossv.expressions;

public class LessThan extends BooleanExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right.getResultClass());
	}

	@Override
	public String toString() {
		return "(" + getExpressionString(left) + " < "
				+ getExpressionString(right) + ")";
	}
}
