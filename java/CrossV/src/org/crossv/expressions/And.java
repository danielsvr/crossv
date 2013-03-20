package org.crossv.expressions;

public class And extends BitwiseExpression {

	public And(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAnd(this);
	}
}