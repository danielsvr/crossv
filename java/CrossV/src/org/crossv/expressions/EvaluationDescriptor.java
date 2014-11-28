package org.crossv.expressions;

public class EvaluationDescriptor {
	private String ifFalseMessage;
	private String ifTrueMessage;
	private Expression test;
	private String scopeDescription;
	private boolean isValidation;
	private boolean isWarning;

	public EvaluationDescriptor(String scopeDescription, Expression test,
			String ifTrueMessage, String ifFalseMessage) {
		this.scopeDescription = scopeDescription;
		this.test = test;
		this.ifTrueMessage = ifTrueMessage;
		this.ifFalseMessage = ifFalseMessage;
	}

	public String getScopeDescription() {
		return scopeDescription;
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

	public boolean isWarning() {
		return isWarning;
	}

	public void isWarning(boolean isWarning) {
		this.isWarning = isWarning;
		this.isValidation = !isWarning;
	}

	public boolean isValidation() {
		return isValidation;
	}

	public void isValidation(boolean isValidation) {
		this.isValidation = isValidation;
		this.isWarning = !isValidation;
	}
}
