package org.crossv.expressions;

public class Cast extends UnaryExpression {
	private final Expression value;
	private Class<?> clazz;

	public Cast(Class<?> clazz, Expression value) {
		this.clazz = clazz;
		this.value = value;
	}

	public Expression getValue() {
		return value;
	}

	@Override
	public Class<?> getResultClass() {
		return clazz;
	}
}
