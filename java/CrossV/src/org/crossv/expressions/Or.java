package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.BitwiseOperationsContext;

public class Or extends BitwiseExpression {
	
	public Or(Expression left, Expression right) {
		super(left, right);
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitOr(this);
	}

	public static Or parse(String text) {
		CrossVParser parser = createTextParser(text);
		BitwiseOperationsContext context = parser.bitwiseOperations();
		return (Or) context.result;
	}
}