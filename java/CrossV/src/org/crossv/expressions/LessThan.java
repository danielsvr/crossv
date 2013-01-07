package org.crossv.expressions;

public class LessThan extends BooleanExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(left);
	}

	@Override
	protected String getOperatorString() {
		return "<";
	}	
}
