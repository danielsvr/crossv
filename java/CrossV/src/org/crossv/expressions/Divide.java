package org.crossv.expressions;

public class Divide extends MultiplicityExpression {
	public Divide(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitDivide(this);
	}
}