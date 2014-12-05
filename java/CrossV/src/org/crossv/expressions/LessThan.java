package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.RelationalOperationsContext;

public class LessThan extends NumericalComparisonExpression {
	public LessThan(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitLessThan(this);
	}

	public static LessThan parse(String text) {
		CrossVParser parser = createTextParser(text);
		RelationalOperationsContext context = parser.relationalOperations();
		return (LessThan) context.result;
	}
}
