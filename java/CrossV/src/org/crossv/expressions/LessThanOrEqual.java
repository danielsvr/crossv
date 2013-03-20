package org.crossv.expressions;

public class LessThanOrEqual extends NumericalComparisonExpression {
	public LessThanOrEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitLessThanOrEqual(this);
	}
}
