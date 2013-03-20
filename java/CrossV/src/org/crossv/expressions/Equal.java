package org.crossv.expressions;

public class Equal extends EqualityExpression {
	public Equal(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitEqual(this);
	}
}
