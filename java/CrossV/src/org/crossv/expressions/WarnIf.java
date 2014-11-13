package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CEvaluatorDescriptor;
import static org.crossv.primitives.ClassDescriptor.CString;

import org.crossv.primitives.ArgumentException;

public class WarnIf extends Expression {

	private Expression scope;
	private Expression test;
	private Expression ifFalse;

	public WarnIf(Expression scope, Expression test, Expression ifFalse) {
		if (scope == null)
			throw new ArgumentException("scope");

		boolean isNotMemberAccess = !(scope instanceof MemberAccess);
		boolean isNotCall = !(scope instanceof Call);
		boolean isNotStringConstant = !(scope instanceof Constant && scope
				.isAssignableTo(CString));
		if (isNotMemberAccess && isNotCall && isNotStringConstant)
			throw new IllegalOperandException();

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
		return CEvaluatorDescriptor;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitWarnIf(this);
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
