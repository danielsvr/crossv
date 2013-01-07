package org.crossv.expressions;


public class AndAlso extends BooleanExpression {

	public AndAlso(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, Boolean.class);
		checkOperandClass(right, Boolean.class);
	}

	@Override
	protected String getOperatorString() {
		return "&&";
	}
}
