package org.crossv.expressions;

public class AndAlso extends ConditionalBinaryExpression {
	public AndAlso(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAndAlso(this);
	}
}
