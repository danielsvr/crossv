package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CNumber;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.UnaryOperationsContext;

public class UnaryPlus extends UnaryExpression {
	public UnaryPlus(Expression operand) {
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
		visitor.visitUnaryPlus(this);
	}

	public static UnaryPlus parse(String text) {
		CrossVParser parser = createTextParser(text);
		UnaryOperationsContext context = parser.unaryOperations();
		return (UnaryPlus) context.result;
	}
}
