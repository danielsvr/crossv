package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CObject;

public final class Instance extends Expression {
	@Override
	public Class<?> getResultClass() {
		return CObject;
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitInstance(this);
	}
	
	@Override
	protected boolean isKnownAtRuntime() {
		return true;
	}
}
