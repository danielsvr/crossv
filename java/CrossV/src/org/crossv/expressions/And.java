package org.crossv.expressions;

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
		CrossVParser parser = createTextParser(text);
		BitwiseContext context = parser.bitwise();
		return (And) context.result;
	}
}