package org.crossv.expressions;

public class Equal extends BinaryExpression {
	public Equal(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
