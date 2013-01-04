package org.crossv.expressions;

public class GreaterThan extends BooleanExpression {
	public GreaterThan(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right.getResultClass());
	}

	@Override
	public String toString() {
		return "(" + getExpressionString(left) + " > "
				+ getExpressionString(right) + ")";
	}
}
