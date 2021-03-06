package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.TermsContext;

public class Cast extends UnaryExpression {
	private Class<?> clazz;

	public Cast(Class<?> clazz, Expression value) {
		super(value);
		this.clazz = clazz;
	}

	@Override
	public Class<?> getResultClass() {
		return clazz;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitCast(this);
	}

	public static Cast parse(String text) {
		CrossVParser parser = createTextParser(text);
		TermsContext context = parser.terms();
		return (Cast) context.result;
	}
}
