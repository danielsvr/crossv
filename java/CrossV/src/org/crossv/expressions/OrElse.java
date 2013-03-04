package org.crossv.expressions;

public class OrElse extends BinaryExpression {
	public OrElse(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, Boolean.class);
		checkOperandClass(right, Boolean.class);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
	
	@Override
	public String getOperatorString() {
		return "||";
	}
}
