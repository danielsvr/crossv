package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.RelationalOperationsContext;

public class GreaterThanOrEqual extends NumericalComparisonExpression {
	public GreaterThanOrEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitGreaterThanOrEqual(this);
	}

	public static GreaterThanOrEqual parse(String text) {
		CrossVParser parser = createTextParser(text);
		RelationalOperationsContext context = parser.relationalOperations();
		return (GreaterThanOrEqual) context.result;
	}
}
