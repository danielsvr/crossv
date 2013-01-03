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
	public ExpressionType getType() {
		return ExpressionType.CONSTANT;
	}
}
