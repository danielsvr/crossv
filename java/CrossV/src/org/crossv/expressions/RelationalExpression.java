package org.crossv.expressions;


public abstract class RelationalExpression extends BinaryExpression {

	public RelationalExpression(Expression left, Expression right) {
		super(left, right);
	}
}
