package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.MultiplicityOperationsContext;

public class Modulo extends MultiplicityExpression {
	public Modulo(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitModulo(this);
	}

	public static Modulo parse(String text) {
		CrossVParser parser = createTextParser(text);
		MultiplicityOperationsContext context = parser.multiplicityOperations();
		return (Modulo) context.result;
	}
}