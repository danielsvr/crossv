package org.crossv.expressions;

import org.crossv.primitives.ArgumentNullException;

public abstract class BinaryExpression extends Expression {
	protected final Expression left;
	protected final Expression right;

	protected final Class<?> leftClass;
	protected final Class<?> rightClass;

	public BinaryExpression(Expression left, Expression right) {
		if (left == null)
			throw new ArgumentNullException("left");
		if (right == null)
			throw new ArgumentNullException("right");
		
		this.left = left;
		this.right = right;
		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitBinary(this);
	}

	public abstract String getOperatorString();

}
