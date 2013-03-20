package org.crossv.expressions;

public class RightShift extends Shift {
	public RightShift(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitRightShift(this);
	}
}