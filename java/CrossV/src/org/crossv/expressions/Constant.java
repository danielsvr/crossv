package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CObject;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.TermContext;

public class Constant extends Expression {
	private final Object value;

	public Constant(Object value) {
		if (value instanceof Expression)
			throw new IllegalOperandException();
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public Class<?> getResultClass() {
		return value != null ? value.getClass() : CObject;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitConstant(this);
	}

	public static Constant parse(String text) {
		CrossVParser parse = createTextParser(text);
		TermContext context = parse.term();
		return (Constant) context.result;
	}
}
