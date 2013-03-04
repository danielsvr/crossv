package org.crossv.expressions;

import org.crossv.primitives.ArgumentNullException;

public abstract class BinaryExpression extends Expression {
	protected final Expression left;
	protected final Expression right;

	public BinaryExpression(Expression left, Expression right) {
		if (left == null)
			throw new ArgumentNullException("left");
		if (right == null)
			throw new ArgumentNullException("right");
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	protected static void checkOperandClass(Expression left, Expression right) {
		if (Number.class.isAssignableFrom(right.getResultClass())
				&& Number.class.isAssignableFrom(left.getResultClass()))
			return;
		if (!right.getResultClass().isAssignableFrom(left.getResultClass())
				&& !left.getResultClass().isAssignableFrom(
						right.getResultClass()))
			throw new IllegalOperandException();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitBinary(this);
	}

	public abstract String getOperatorString();
}
