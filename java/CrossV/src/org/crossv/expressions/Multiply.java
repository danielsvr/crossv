package org.crossv.expressions;

public class Multiply extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Multiply(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (Long.class.isAssignableFrom(leftClass)) {
			if (Float.class.isAssignableFrom(rightClass)) {
				resultClass = Float.class;
				return;
			} else if (Double.class.isAssignableFrom(rightClass)) {
				resultClass = Double.class;
				return;
			} else if (Number.class.isAssignableFrom(rightClass)) {
				resultClass = Long.class;
				return;
			}
		} else if (Float.class.isAssignableFrom(leftClass)) {
			if (Double.class.isAssignableFrom(rightClass)) {
				resultClass = Double.class;
				return;
			} else if (Number.class.isAssignableFrom(rightClass)) {
				resultClass = Float.class;
				return;
			}
		} else if (Double.class.isAssignableFrom(leftClass)) {
			if (Number.class.isAssignableFrom(rightClass)) {
				resultClass = Double.class;
				return;
			}
		} else if (Number.class.isAssignableFrom(leftClass)) {
			if (Long.class.isAssignableFrom(rightClass)) {
				resultClass = Long.class;
				return;
			} else if (Float.class.isAssignableFrom(rightClass)) {
				resultClass = Float.class;
				return;
			} else if (Double.class.isAssignableFrom(rightClass)) {
				resultClass = Double.class;
				return;
			} else if (Number.class.isAssignableFrom(rightClass)) {
				resultClass = Integer.class;
				return;
			}
		}

		throw new IllegalOperandException();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}
}