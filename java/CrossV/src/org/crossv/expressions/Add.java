package org.crossv.expressions;

public class Add extends BinaryExpression {
	public Add(Expression left, Expression right) {
		super(left, right);
		checkOperandClass(left, right);
	}

	@Override
	public Class<?> getResultClass() {
		Class<?> leftClass = left.getResultClass();
		Class<?> rightClass = right.getResultClass();

		if (isFirtsParameterByte(leftClass, rightClass))
			return rightClass;
		if (isFirtsParameterByte(rightClass, leftClass))
			return leftClass;
		if (isFirtsParameterShort(leftClass, rightClass))
			return rightClass;
		if (isFirtsParameterShort(rightClass, leftClass))
			return leftClass;
		if (isFirtsParameterInteger(leftClass, rightClass))
			return rightClass;
		if (isFirtsParameterInteger(rightClass, leftClass))
			return leftClass;


		// if (String.class.isAssignableFrom(leftClass)
		// || String.class.isAssignableFrom(rightClass))
		// return String.class;
		return Object.class;
	}

	private boolean isFirtsParameterByte(Class<?> first, Class<?> second) {
		return Byte.class.isAssignableFrom(first)
				&& Number.class.isAssignableFrom(second) 
				&& !Byte.class.isAssignableFrom(second);
	}

	private boolean isFirtsParameterShort(Class<?> first, Class<?> second) {
		return Byte.class.isAssignableFrom(first)
				&& Number.class.isAssignableFrom(second) 
				&& !Byte.class.isAssignableFrom(second)
				&& !Short.class.isAssignableFrom(second);
	}

	private boolean isFirtsParameterInteger(Class<?> first, Class<?> second) {
		return Byte.class.isAssignableFrom(first)
				&& Number.class.isAssignableFrom(second) 
				&& !Byte.class.isAssignableFrom(second)
				&& !Short.class.isAssignableFrom(second) 
				&& !Integer.class.isAssignableFrom(second);
	}
}