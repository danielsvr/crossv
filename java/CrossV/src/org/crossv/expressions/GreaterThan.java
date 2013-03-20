package org.crossv.expressions;

public class GreaterThan extends NumericalComparisonExpression {
	public GreaterThan(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitGreaterThan(this);
	}
}
