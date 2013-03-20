package org.crossv.expressions;

public class GreaterThanOrEqual extends NumericalComparisonExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitGreaterThanOrEqual(this);
	}
}
