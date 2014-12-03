package org.crossv.expressions;

import java.io.Reader;
import java.io.StringReader;

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
		Reader reader = new StringReader(text);
		CrossVParser parser = createParser(reader, true);
		LogicalContext context = parser.logical();
		return (AndAlso) context.result;
	}
}
