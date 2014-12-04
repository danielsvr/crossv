package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.MultiplyContext;

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
		MultiplyContext context = parser.multiply();
		return (Multiply) context.result;
	}
}