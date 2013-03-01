package org.crossv.expressions;

public class Or extends BinaryExpression {
	private Class<?> resultClass;
	private Class<?> leftClass;
	private Class<?> rightClass;

	public Or(Expression left, Expression right) {
		super(left, right);

		this.leftClass = left.getResultClass();
		this.rightClass = right.getResultClass();

		if (Boolean.class.isAssignableFrom(leftClass)
				&& Boolean.class.isAssignableFrom(rightClass)) {
			resultClass = Boolean.class;
			return;
		}
		if (Long.class.isAssignableFrom(leftClass)
				|| Long.class.isAssignableFrom(rightClass)) {
			resultClass = Long.class;
			return;
		} else if (Number.class.isAssignableFrom(leftClass)
				&& Number.class.isAssignableFrom(rightClass)) {
			resultClass = Integer.class;
			return;
		}
		throw new IllegalOperandException();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}
}