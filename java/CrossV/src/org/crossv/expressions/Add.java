package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CString;
import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.AdditiveOperationsContext;

public class Add extends AdditiveExpression {

	public Add(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		resultClass = calculateResultClass();
	}

	private Class<?> calculateResultClass() {
		if (canPromoteNumbers(leftClass, rightClass))
			return getNumericPromotion(leftClass, rightClass);
		return CString;
	}

	private void verifyOperands() {
		if (!left.isAssignableTo(CString) && !right.isAssignableTo(CString)
				&& !canPromoteNumbers(leftClass, rightClass))
			throw illegalOperand();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAdd(this);
	}

	public static Add parse(String text) {
		CrossVParser parser = createTextParser(text);
		AdditiveOperationsContext context = parser.additiveOperations();
		return (Add) context.result;
	}
}