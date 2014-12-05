package org.crossv.expressions;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.ShiftOperationsContext;

public class RightShift extends ShiftExpression {
	public RightShift(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitRightShift(this);
	}

	public static RightShift parse(String text) {
		CrossVParser parser = createTextParser(text);
		ShiftOperationsContext context = parser.shiftOperations();
		return (RightShift) context.result;
	}
}