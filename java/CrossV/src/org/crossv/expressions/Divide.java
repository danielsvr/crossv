package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.MultiplicityOperationsContext;

public class Divide extends MultiplicityExpression {
	public Divide(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitDivide(this);
	}

	public static Divide parse(String text) {
		CrossVParser parser = createTextParser(text);
		MultiplicityOperationsContext context = parser.multiplicityOperations();
		return (Divide) context.result;
	}
}