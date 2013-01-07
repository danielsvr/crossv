package org.crossv.expressions;

public class GreaterThan extends BooleanExpression {
	public GreaterThan(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(left);
	}

	@Override
	protected String getOperatorString() {
		return ">";
	}	
}
