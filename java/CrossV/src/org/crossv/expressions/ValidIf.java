package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CEvaluatorDescriptor;
import static org.crossv.primitives.ClassDescriptor.CString;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.ValidationEvaluationsContext;
import org.crossv.primitives.ArgumentException;

public class ValidIf extends Expression {

	private Expression scope;
	private Expression test;
	private Expression ifFalse;

	public ValidIf(Expression scope, Expression test, Expression ifFalse) {
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

	public static ValidIf parse(String text) {
		CrossVParser parser = createTextParser(text);
		ValidationEvaluationsContext context = parser.validationEvaluations();
		return (ValidIf) context.result;
	}
}
