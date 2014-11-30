package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CObject;

public class Constant extends Expression {
	private final Object value;

	public Constant(Object value) {
		if (value instanceof Expression)
			throw new IllegalOperandException();
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public Class<?> getResultClass() {
		return value != null ? value.getClass() : CObject;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitConstant(this);
	}
}
