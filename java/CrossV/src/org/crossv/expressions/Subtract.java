package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.AddContext;

public class Subtract extends AdditiveExpression {
	private Class<?> resultClass;

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
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSubtract(this);
	}

	public static Subtract parse(String text) {
		CrossVParser parser = createTextParser(text);
		AddContext context = parser.add();
		return (Subtract) context.result;
	}
}