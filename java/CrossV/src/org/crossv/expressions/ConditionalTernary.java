package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CByte;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CObject;
import static org.crossv.primitives.ClassDescriptor.CShort;
import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.AnyExpressionsContext;
import org.crossv.primitives.ArgumentNullException;

public class ConditionalTernary extends Expression {
	private Expression test;
	private Expression ifTrue;
	private Expression ifFalse;
	private Class<?> resultClass;

	public ConditionalTernary(Expression test, Expression ifTrue,
			Expression ifFalse) {
		if (test == null)
			throw new ArgumentNullException("test");
		if (ifTrue == null)
			throw new ArgumentNullException("ifTrue");
		if (ifFalse == null)
			throw new ArgumentNullException("ifFalse");
		this.test = test;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;

		verifyOperands();
		resultClass = calculateResultClass();
	}

	private void verifyOperands() {
		if (!test.isAssignableTo(CBoolean) || isNullConstant(ifTrue)
				&& isNullConstant(ifFalse))
			throw illegalOperand();
	}

	private Class<?> calculateResultClass() {
		Class<?> ifTrueClass = ifTrue.getResultClass();
		Class<?> ifFalseClass = ifFalse.getResultClass();

		if (isNullConstant(ifTrue))
			return ifFalseClass;
		if (isNullConstant(ifFalse))
			return ifTrueClass;

		// @formatter:off

		if (ifTrueClass.equals(ifFalseClass)
				|| (ifTrue.isAssignableToAny(CByte, CShort) && ifFalse
						.isAssignableTo(CInteger))
				|| (ifTrue.isAssignableTo(CShort) && ifFalse
						.isAssignableTo(CByte)))
			return ifTrueClass;

		if ((ifTrue.isAssignableTo(CInteger) && ifFalse.isAssignableToAny(
				CByte, CShort))
				|| (ifTrue.isAssignableTo(CByte) && ifFalse
						.isAssignableTo(CShort)))
			return ifFalseClass;

		// @formatter:on
		if (canPromoteNumbers(ifTrueClass, ifFalseClass))
			return getNumericPromotion(ifTrueClass, ifFalseClass);

		return CObject;
	}

	private boolean isNullConstant(Expression expression) {
		return expression instanceof Constant
				&& ((Constant) expression).getValue() == null;
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitConditional(this);
	}

	public Expression getTest() {
		return test;
	}

	public Expression getIfTrue() {
		return ifTrue;
	}

	public Expression getIfFalse() {
		return ifFalse;
	}

	public static ConditionalTernary parse(String text) {
		CrossVParser parser = createTextParser(text);
		AnyExpressionsContext context = parser.anyExpressions();
		return (ConditionalTernary) context.result;
	}
}
