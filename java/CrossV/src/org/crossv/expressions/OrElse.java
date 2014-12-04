package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.LogicalContext;

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
		LogicalContext context = parser.logical();
		return (OrElse) context.result;
	}
}
