package org.crossv.expressions;

public class AndAlso extends BinaryExpression {
	public AndAlso(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, Boolean.class);
		checkOperandClass(right, Boolean.class);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
