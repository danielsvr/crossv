package org.crossv.expressions;

public class AndAlso extends BinaryExpression {
	public AndAlso(Expression left, Expression right) {
		super(left, right);

		Class<?> leftClass = left.getResultClass();
		Class<?> rightClass = right.getResultClass();

		if (!Boolean.class.isAssignableFrom(leftClass)
				|| !Boolean.class.isAssignableFrom(rightClass))
			throw new IllegalOperandException();
	}

	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}

	@Override
	public String getOperatorString() {
		return "&&";
	}
}
