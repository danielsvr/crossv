package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.EqualityOperationsContext;

public class NotEqual extends EqualityExpression {
	public NotEqual(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitNotEqual(this);
	}

	public static NotEqual parse(String text) {
		CrossVParser parser = createTextParser(text);
		EqualityOperationsContext context = parser.equalityOperations();
		return (NotEqual) context.result;
	}
}
