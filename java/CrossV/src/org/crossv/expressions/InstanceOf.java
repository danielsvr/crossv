package org.crossv.expressions;

public class InstanceOf extends BinaryExpression {
	public InstanceOf(Expression left, Expression right) {
		super(left, right);
		checkIfReturnsReference(left);
		checkOperandClass(right, Class.class);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
