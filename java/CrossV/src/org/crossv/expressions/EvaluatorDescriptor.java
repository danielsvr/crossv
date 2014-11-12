package org.crossv.expressions;

public class EvaluatorDescriptor {
	private String ifFalseMessage;
	private Expression test;
	private EvaluatorScope scope;

	public EvaluatorDescriptor(EvaluatorScope scope, Expression test,
			String ifFalseMessage) {
		this.scope = scope;
		this.test = test;
		this.ifFalseMessage = ifFalseMessage;
	}

	public String getScopeDescription() {
		return scope.getDescription();
	}

	public String getIfFalseMessage() {
		return ifFalseMessage;
	}

	public Expression getTest() {
		return test;
	}
}
