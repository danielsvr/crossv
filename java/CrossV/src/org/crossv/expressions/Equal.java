package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.RelationContext;

public class Equal extends EqualityExpression {
	public Equal(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitEqual(this);
	}

	public static Equal parse(String text) {
		CrossVParser parser = createTextParser(text);
		RelationContext context = parser.relation();
		return (Equal) context.result;
	}
}
