package org.crossv.expressions;

public class Devide extends MultiplicityExpression {
	public Devide(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitDevide(this);
	}
}