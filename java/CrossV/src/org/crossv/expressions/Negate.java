package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CNumber;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.UnaryOperationsContext;

public class Negate extends UnaryExpression {
	public Negate(Expression operand) {
		super(operand);
		verifyOperand();
	}

	private void verifyOperand() {
		if (!operand.isAssignableTo(CNumber))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return operand.getResultClass();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNegate(this);
	}

	public static Negate parse(String text) {
		CrossVParser parser = createTextParser(text);
		UnaryOperationsContext context = parser.unaryOperations();
		return (Negate) context.result;
	}
}
