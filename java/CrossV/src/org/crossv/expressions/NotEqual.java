package org.crossv.expressions;

public class NotEqual extends EqualityExpression {
	public NotEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNotEqual(this);
	}
}
