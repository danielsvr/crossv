package org.crossv.expressions;

public class Or extends BitwiseExpression {
	
	public Or(Expression left, Expression right) {
		super(left, right);
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitOr(this);
	}
}