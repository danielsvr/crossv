package org.crossv.expressions;

import org.crossv.primitives.ArgumentException;

public class ValidIf extends Expression {

	private Expression scope;
	private Expression test;
	private Expression ifFalse;

	public ValidIf(Expression scope, Expression test, Expression ifFalse) {
		if (scope == null)
			throw new ArgumentException("scope");
		if (test == null)
			throw new ArgumentException("test");
		if (ifFalse == null)
			throw new ArgumentException("ifFalse");
		this.scope = scope;
		this.test = test;
		this.ifFalse = ifFalse;
	}

	@Override
	public Class<?> getResultClass() {
		return EvaluatorDescriptor.class;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitValidIf(this);
	}

	public Expression getScope() {
		return scope;
	}

	public Expression getTest() {
		return test;
	}

	public Expression getIfFalse() {
		return ifFalse;
	}
}
