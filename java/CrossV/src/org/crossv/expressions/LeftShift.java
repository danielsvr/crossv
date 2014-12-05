package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.ShiftOperationsContext;

public class LeftShift extends ShiftExpression {

	public LeftShift(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitLeftShift(this);
	}

	public static LeftShift parse(String text) {
		CrossVParser parser = createTextParser(text);
		ShiftOperationsContext context = parser.shiftOperations();
		return (LeftShift) context.result;
	}
}