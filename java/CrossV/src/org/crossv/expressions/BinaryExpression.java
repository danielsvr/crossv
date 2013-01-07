package org.crossv.expressions;

import static java.text.MessageFormat.format;

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
	
	@Override
	public String toString() {
		return format("({0} {1} {2})", left, getOperatorString(), right);
	}

	protected abstract String getOperatorString();
	
	protected static void checkOperandClass(Expression left, Expression right) {
		if(!right.getResultClass().isAssignableFrom(left.getResultClass())
				&& !left.getResultClass().isAssignableFrom(right.getResultClass()))
			throw new IllegalOperandException();
	}
}
