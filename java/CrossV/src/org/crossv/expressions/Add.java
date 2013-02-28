package org.crossv.expressions;

public class Add extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Add(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (String.class.isAssignableFrom(leftClass)
				|| String.class.isAssignableFrom(rightClass)) {
			resultClass = String.class;
			return;
		} else if (Byte.class.isAssignableFrom(leftClass)
				|| Short.class.isAssignableFrom(leftClass)
				|| Integer.class.isAssignableFrom(leftClass)) {
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
		} else if (Long.class.isAssignableFrom(leftClass)) {
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
		} else if (Double.class.isAssignableFrom(leftClass)
				&& Number.class.isAssignableFrom(rightClass)) {
			resultClass = Double.class;
			return;
		}
		throw new IllegalOperandException();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}
}