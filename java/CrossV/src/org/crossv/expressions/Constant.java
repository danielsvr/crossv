package org.crossv.expressions;

public class Constant extends Expression {
	private final Object value;

	public Constant(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		if (value == null)
			return "null";

		if (value instanceof String)
			return valueAsString();

		if (value instanceof Class)
			return valueAsClass();

		return value.toString();
	}

	private String valueAsClass() {
		return ((Class<?>) value).getName();
	}

	private String valueAsString() {
		return "\"" + value.toString() + "\"";
	}

	@Override
	public Class<?> getResultClass() {
		return value != null ? value.getClass() : Object.class;
	}
}
