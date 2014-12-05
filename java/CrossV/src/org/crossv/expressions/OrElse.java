package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.LogicalOperationsContext;

public class OrElse extends ConditionalBinaryExpression {
	public OrElse(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitOrElse(this);
	}

	public static OrElse parse(String text) {
		CrossVParser parser = createTextParser(text);
		LogicalOperationsContext context = parser.logicalOperations();
		return (OrElse) context.result;
	}
}
