package org.crossv.expressions;

public class Add extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Add(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (String.class.isAssignableFrom(leftClass))
			checkOperandClass(right, Number.class);
		else if (String.class.isAssignableFrom(rightClass))
			checkOperandClass(left, Number.class);
		else
			checkOperandClass(left, right);

		this.resultClass = computeResultClass();
	}

	private Class<?> computeResultClass() {
		if (String.class.isAssignableFrom(leftClass)
				|| String.class.isAssignableFrom(rightClass))
			return String.class;

		if (leftClass.equals(rightClass))
			return leftClass;

		if (Byte.class.isAssignableFrom(leftClass))
			return rightClass;
		if (Byte.class.isAssignableFrom(rightClass))
			return leftClass;
		if (Short.class.isAssignableFrom(leftClass))
			return rightClass;
		if (Short.class.isAssignableFrom(rightClass))
			return leftClass;
		if (Integer.class.isAssignableFrom(leftClass))
			return rightClass;
		if (Integer.class.isAssignableFrom(rightClass))
			return leftClass;
		if (Long.class.isAssignableFrom(leftClass))
			return rightClass;
		if (Long.class.isAssignableFrom(rightClass))
			return leftClass;
		if (Float.class.isAssignableFrom(leftClass))
			return rightClass;
		return leftClass;
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}
}