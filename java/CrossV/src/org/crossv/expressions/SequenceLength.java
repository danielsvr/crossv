package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CEnumeration;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CIterable;
import static org.crossv.primitives.ClassDescriptor.CString;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.AnyExpressionsContext;

public class SequenceLength extends UnaryExpression {

	public SequenceLength(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isKnownOnlyAtRuntime()
				&& !operand.isAssignableToAny(CString, CIterable, CEnumeration)
				&& !operand.isArray())
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CInteger;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSequenceLength(this);
	}

	public static SequenceLength parse(String text) {
		CrossVParser parser = createTextParser(text);
		AnyExpressionsContext context = parser.anyExpressions();
		return (SequenceLength) context.result;
	}
}
