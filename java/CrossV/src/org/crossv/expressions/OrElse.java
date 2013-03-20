package org.crossv.expressions;

public class OrElse extends ConditionalBinaryExpression {
	public OrElse(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitOrElse(this);
	}
}
