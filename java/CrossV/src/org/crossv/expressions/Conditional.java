package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

import org.crossv.primitives.ArgumentNullException;

public class Conditional extends Expression {
	private Expression test;
	private Expression ifTrue;
	private Expression ifFalse;
	private Class<?> resultClass;

	public Conditional(Expression test, Expression ifTrue, Expression ifFalse) {
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
		// @formatter:off
		if (!test.isAssignableTo(Boolean.class) 
			 || ifTrue.isNullConstant() && ifFalse.isNullConstant())
			throw illegalOperand();
		// @formatter:on
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
			|| (ifTrue.isAssignableToAny(Byte.class, Short.class) && ifFalse.isAssignableTo(Integer.class))
			|| (ifTrue.isAssignableTo(Short.class) && ifFalse.isAssignableTo(Byte.class)))			
			return ifTrueClass;

		if ((ifTrue.isAssignableTo(Integer.class) && ifFalse.isAssignableToAny(Byte.class, Short.class))
			|| (ifTrue.isAssignableTo(Byte.class) && ifFalse.isAssignableTo(Short.class)))			
			return ifFalseClass;
		
		// @formatter:on

		if (canPromoteNumbers(ifTrueClass, ifFalseClass))
			return getNumericPromotion(ifTrueClass, ifFalseClass);

		return Object.class;
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
