package org.crossv.expressions;

public abstract class BinaryOperation extends Expression {
	private final Expression left;
	private final Expression right;

	public BinaryOperation(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
}
