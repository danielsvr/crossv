package org.crossv.expressions;

public class Modulo extends MultiplicityExpression {
	public Modulo(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitModulo(this);
	}
}