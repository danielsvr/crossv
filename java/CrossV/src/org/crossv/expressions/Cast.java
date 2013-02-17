package org.crossv.expressions;

public class Cast extends UnaryExpression {
	private Class<?> clazz;

	public Cast(Class<?> clazz, Expression value) {
		super(value);
		this.clazz = clazz;
	}

	@Override
	public Class<?> getResultClass() {
		return clazz;
	}
}
