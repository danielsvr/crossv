package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.UnaryOperationsContext;

public class Not extends UnaryExpression {
	public Not(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isAssignableTo(CBoolean))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNot(this);
	}

	public static Not parse(String text) {
		CrossVParser parser = createTextParser(text);
		UnaryOperationsContext context = parser.unaryOperations();
		return (Not) context.result;
	}
}
