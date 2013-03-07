package org.crossv.expressions;

public final class Context extends Expression {

	private final Class<?> clazz;

	public Context() {
		this.clazz = Object.class;
	}

	public Context(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public Class<?> getResultClass() {
		return clazz;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitContext(this);
	}
}
