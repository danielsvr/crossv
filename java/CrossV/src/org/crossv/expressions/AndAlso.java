package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.LogicalOperationsContext;

public class AndAlso extends ConditionalBinaryExpression {
	public AndAlso(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAndAlso(this);
	}

	public static AndAlso parse(String text) {
		CrossVParser parser = createTextParser(text);
		LogicalOperationsContext context = parser.logicalOperations();
		return (AndAlso) context.result;
	}
}
