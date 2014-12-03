package org.crossv.expressions;

import java.io.Reader;
import java.io.StringReader;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.CastContext;

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
		Reader reader = new StringReader(text);
		CrossVParser parser = createParser(reader, true);
		CastContext context = parser.cast();
		return (Cast) context.result;
	}
}
