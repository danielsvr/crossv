package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.RelationalOperationsContext;

public class GreaterThan extends NumericalComparisonExpression {
	public GreaterThan(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitGreaterThan(this);
	}

	public static GreaterThan parse(String text) {
		CrossVParser parser = createTextParser(text);
		RelationalOperationsContext context = parser.relationalOperations();
		return (GreaterThan) context.result;
	}
}
