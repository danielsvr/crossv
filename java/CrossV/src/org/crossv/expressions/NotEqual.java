package org.crossv.expressions;

public class NotEqual extends BinaryExpression {
	public NotEqual(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
	
	@Override
	public String getOperatorString() {
		return "!=";
	}
}
