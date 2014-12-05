package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.MultiplicityOperationsContext;

public class Multiply extends MultiplicityExpression {
	public Multiply(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitMultiply(this);
	}

	public static Multiply parse(String text) {
		CrossVParser parser = createTextParser(text);
		MultiplicityOperationsContext context = parser.multiplicityOperations();
		return (Multiply) context.result;
	}
}