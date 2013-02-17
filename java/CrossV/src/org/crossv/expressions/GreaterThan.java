package org.crossv.expressions;

public class GreaterThan extends BinaryExpression {
	public GreaterThan(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(left);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
