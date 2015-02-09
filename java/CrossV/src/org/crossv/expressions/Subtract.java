package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.AdditiveOperationsContext;

public class Subtract extends AdditiveExpression {

	public Subtract(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		resultClass = getNumericPromotion(leftClass, rightClass);
	}

	private void verifyOperands() {
		if (!canPromoteNumbers(leftClass, rightClass))
			throw illegalOperand();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSubtract(this);
	}

	public static Subtract parse(String text) {
		CrossVParser parser = createTextParser(text);
		AdditiveOperationsContext context = parser.additiveOperations();
		return (Subtract) context.result;
	}
}