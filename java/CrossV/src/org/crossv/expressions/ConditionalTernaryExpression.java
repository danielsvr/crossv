package org.crossv.expressions;

import static org.crossv.expressions.ExpressionClass.CBoolean;
import static org.crossv.expressions.ExpressionClass.CByte;
import static org.crossv.expressions.ExpressionClass.CInteger;
import static org.crossv.expressions.ExpressionClass.CObject;
import static org.crossv.expressions.ExpressionClass.CShort;
import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

import org.crossv.primitives.ArgumentNullException;

public class ConditionalTernaryExpression extends Expression {
	private Expression test;
	private Expression ifTrue;
	private Expression ifFalse;
	private Class<?> resultClass;

	public ConditionalTernaryExpression(Expression test, Expression ifTrue,
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
		if (!test.isAssignableTo(CBoolean) || ifTrue.isNullConstant()
				&& ifFalse.isNullConstant())
			throw illegalOperand();
	}

	private Class<?> calculateResultClass() {
		Class<?> ifTrueClass = ifTrue.getResultClass();
		Class<?> ifFalseClass = ifFalse.getResultClass();

		if (ifTrue.isNullConstant())
			return ifFalseClass;
		if (ifFalse.isNullConstant())
			return ifTrueClass;

		// @formatter:off
		
		if (ifTrueClass.equals(ifFalseClass)
			|| (ifTrue.isAssignableToAny(CByte, CShort) && ifFalse.isAssignableTo(CInteger))
			|| (ifTrue.isAssignableTo(CShort) && ifFalse.isAssignableTo(CByte)))			
			return ifTrueClass;

		if ((ifTrue.isAssignableTo(CInteger) && ifFalse.isAssignableToAny(CByte, CShort))
			|| (ifTrue.isAssignableTo(CByte) && ifFalse.isAssignableTo(CShort)))			
			return ifFalseClass;
		
		// @formatter:on
		if (canPromoteNumbers(ifTrueClass, ifFalseClass))
			return getNumericPromotion(ifTrueClass, ifFalseClass);

		return CObject;
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
}
