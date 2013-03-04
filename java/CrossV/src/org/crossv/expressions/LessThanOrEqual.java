package org.crossv.expressions;

public class LessThanOrEqual extends BinaryExpression {
	public LessThanOrEqual(Expression left, Expression right) {
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
		return "<=";
	}
}
