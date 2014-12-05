package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.BitwiseOperationsContext;

public class Xor extends BitwiseExpression {

	public Xor(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitXor(this);
	}

	public static Xor parse(String text) {
		CrossVParser parser = createTextParser(text);
		BitwiseOperationsContext context = parser.bitwiseOperations();
		return (Xor) context.result;
	}
}