package org.crossv.expressions;

public class LessThan extends BinaryExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(left);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
	
	@Override
	public String getOperatorString() {
		return "<";
	}
}
