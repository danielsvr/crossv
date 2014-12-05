package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CEvaluatorDescriptor;
import static org.crossv.primitives.ClassDescriptor.CString;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.WarningEvaluationsContext;
import org.crossv.primitives.ArgumentException;

public class WarnIf extends Expression {

	private Expression scope;
	private Expression test;
	private Expression ifTrue;

	public WarnIf(Expression scope, Expression test, Expression ifTrue) {
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
		if (ifTrue == null)
			throw new ArgumentException("ifFalse");
		this.scope = scope;
		this.test = test;
		this.ifTrue = ifTrue;
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

	public Expression getIfTrue() {
		return ifTrue;
	}

	public static WarnIf parse(String text) {
		CrossVParser parser = createTextParser(text);
		WarningEvaluationsContext context = parser.warningEvaluations();
		return (WarnIf) context.result;
	}
}
