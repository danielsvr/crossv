package org.crossv.expressions;

public class LessThan extends NumericalComparisonExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitLessThan(this);
	}
}
