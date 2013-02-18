package org.crossv.expressions;

public class Not extends UnaryExpression {
	public Not(Expression operand) {
		super(operand);
		checkOperandClass(operand, Boolean.class);
	}
	
	@Override
	public Class<?> getResultClass() {
		return Boolean.class;
	}
}
