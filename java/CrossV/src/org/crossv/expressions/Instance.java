package org.crossv.expressions;

public final class Instance extends Expression {
	@Override
	public Class<?> getResultClass() {
		return Object.class;
	}
}
