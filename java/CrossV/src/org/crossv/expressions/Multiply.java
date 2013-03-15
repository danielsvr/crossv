package org.crossv.expressions;

public class Multiply extends MultiplicityExpression {
	public Multiply(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitMultiply(this);
	}
}