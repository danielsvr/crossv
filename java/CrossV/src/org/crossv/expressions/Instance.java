package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CObject;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.TermsContext;

public final class Instance extends Expression {
	@Override
	public Class<?> getResultClass() {
		return CObject;
	}
	
	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitInstance(this);
	}
	
	@Override
	protected boolean isKnownOnlyAtRuntime() {
		return true;
	}

	public static Instance parse(String text) {
		CrossVParser parser = createTextParser(text);
		TermsContext context = parser.terms();
		return (Instance) context.result;
	}
}
