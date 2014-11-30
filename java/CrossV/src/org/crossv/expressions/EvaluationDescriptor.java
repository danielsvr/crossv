package org.crossv.expressions;

public class EvaluationDescriptor {
	private Expression ifFalse;
	private Expression ifTrue;
	private Expression test;
	private String scopeDescription;
	private boolean isValidation;
	private boolean isWarning;

	public EvaluationDescriptor(String scopeDescription, Expression test,
			Expression ifTrue, Expression ifFalse) {
		this.scopeDescription = scopeDescription;
		this.test = test;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	public String getScopeDescription() {
		return scopeDescription;
	}

	public Expression getIfTrue() {
		return ifTrue;
	}

	public Expression getIfFalse() {
		return ifFalse;
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
