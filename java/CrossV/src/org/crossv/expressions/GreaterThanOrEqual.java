package org.crossv.expressions;

public class GreaterThanOrEqual extends BinaryExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(right);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
