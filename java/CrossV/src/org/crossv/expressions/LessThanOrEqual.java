package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.RelationContext;

public class LessThanOrEqual extends NumericalComparisonExpression {
	public LessThanOrEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitLessThanOrEqual(this);
	}

	public static LessThanOrEqual parse(String text) {
		CrossVParser parser = createTextParser(text);
		RelationContext context = parser.relation();
		return (LessThanOrEqual) context.result;
	}
}
