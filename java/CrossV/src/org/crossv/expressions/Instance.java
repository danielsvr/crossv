package org.crossv.expressions;

public final class Instance extends Expression {
	@Override
	public Class<?> getResultClass() {
		return Object.class;
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitInstance(this);
	}
	
	@Override
	protected boolean isInstance() {
		return true;
	}
}
