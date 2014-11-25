package org.crossv.expressions;

public class EvaluationDescriptor {
	private String ifFalseMessage;
	private String ifTrueMessage;
	private Expression test;
	private EvaluatorScope scope;

	public EvaluationDescriptor(EvaluatorScope scope, Expression test,
			String ifTrueMessage, String ifFalseMessage) {
		this.scope = scope;
		this.test = test;
		this.ifTrueMessage = ifTrueMessage;
		this.ifFalseMessage = ifFalseMessage;
	}

	public String getScopeDescription() {
		return scope.getDescription();
	}

	public String getIfTrueMessage() {
		return ifTrueMessage;
	}

	public String getIfFalseMessage() {
		return ifFalseMessage;
	}

	public Expression getTest() {
		return test;
	}
}
