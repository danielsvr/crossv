package org.crossv.expressions;

public class ValidIf extends Expression {

	private Expression scope;
	private Expression test;
	private String ifFalseMessage;

	public ValidIf(Expression scope, Expression test, String ifFalseMessage) {
		this.scope = scope;
		this.test = test;
		this.ifFalseMessage = ifFalseMessage;
		
	}

	@Override
	public Class<?> getResultClass() {
		return EvaluatorDescriptor.class;
	}

}
