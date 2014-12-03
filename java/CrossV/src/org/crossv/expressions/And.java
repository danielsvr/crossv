package org.crossv.expressions;

import java.io.Reader;
import java.io.StringReader;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.BitwiseContext;

public class And extends BitwiseExpression {

	public And(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAnd(this);
	}

	public static And parse(String text) {
		Reader reader = new StringReader(text); 
		CrossVParser parser = createParser(reader, true);
		BitwiseContext context = parser.bitwise();
		return (And) context.result;
	}
}