package org.crossv.expressions;

public class Xor extends BitwiseExpression {

	public Xor(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitXor(this);
	}
}