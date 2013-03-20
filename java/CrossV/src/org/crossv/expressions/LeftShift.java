package org.crossv.expressions;

public class LeftShift extends ShiftExpression {

	public LeftShift(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitLeftShift(this);
	}
}