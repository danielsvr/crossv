package org.crossv.expressions;

public class OrElse extends BooleanExpression {

	public OrElse(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, Boolean.class);
		checkOperandClass(right, Boolean.class);
	}

	@Override
	protected String getOperatorString() {
		return "||";
	}	
}
