package org.crossv.expressions;

public class LessThanOrEqual extends BooleanExpression {
	public LessThanOrEqual(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
		checkIfReturnsPrimitive(left);
		checkIfReturnsPrimitive(left);
	}
}
