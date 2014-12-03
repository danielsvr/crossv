package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.LogicalContext;

public class AndAlso extends ConditionalBinaryExpression {
	public AndAlso(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAndAlso(this);
	}

	public static AndAlso parse(String text) {
		CrossVParser parser = createTextParser(text);
		LogicalContext context = parser.logical();
		return (AndAlso) context.result;
	}
}
