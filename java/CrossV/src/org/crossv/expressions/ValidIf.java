package org.crossv.expressions;

import static org.crossv.primitives.Strings.isNullOrWhitespace;

import org.crossv.primitives.ArgumentException;

public class ValidIf extends Expression {

	private Expression scope;
	private Expression test;
	private String ifFalseMessage;

	public ValidIf(Expression scope, Expression test, String ifFalseMessage) {
		if (scope == null)
			throw new ArgumentException("scope");
		if (test == null)
			throw new ArgumentException("test");
		if (isNullOrWhitespace(ifFalseMessage))
			throw new ArgumentException("ifFalseMessage");
		this.scope = scope;
		this.test = test;
		this.ifFalseMessage = ifFalseMessage;
	}

	@Override
	public Class<?> getResultClass() {
		return EvaluatorDescriptor.class;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitValidIf(this);
	}
}
