package org.crossv.expressions;

public class GreaterThanOrEqual extends BooleanExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right.getResultClass());
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(right);
	}

	@Override
	protected String getOperatorString() {
		return ">=";
	}	
}
