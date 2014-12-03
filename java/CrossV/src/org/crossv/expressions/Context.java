package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CObject;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.TermContext;

public final class Context extends Expression {

	private final Class<?> clazz;

	public Context() {
		this.clazz = CObject;
	}

	public Context(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public Class<?> getResultClass() {
		return clazz;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitContext(this);
	}

	@Override
	protected boolean isKnownOnlyAtRuntime() {
		return true;
	}

	public static Context parse(String text) {
		CrossVParser parser = createTextParser(text);
		TermContext context = parser.term();
		return (Context) context.result;
	}
}
