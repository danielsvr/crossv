package org.crossv.expressions;

public class InstanceOf extends BooleanExpression {
	public InstanceOf(Expression left, Expression right) {
		super(left, right);
		checkIfReturnsReference(left);
		checkOperandClass(right, Class.class);
	}
}
