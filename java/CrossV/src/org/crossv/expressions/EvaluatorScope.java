package org.crossv.expressions;

public class EvaluatorScope {

	private Object scope;
	private String description;

	public EvaluatorScope(Object scope, String description) {
		this.scope = scope;
		this.description = description;
	}

	public Object getScopeObject() {
		return scope;
	}

	public String getDescription() {
		return description;
	}
}
