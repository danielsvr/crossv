package org.crossv.expressions;

public class GreaterThanOrEqual extends BooleanExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(right);
	}
}
