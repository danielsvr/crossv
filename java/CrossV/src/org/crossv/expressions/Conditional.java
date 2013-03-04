package org.crossv.expressions;

import static org.crossv.primitives.ExpressionUtil.canPerformNumericPromotionForAll;
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

		checkOperandClass(test, Boolean.class);
		if (ifTrue instanceof Constant && ifFalse instanceof Constant
				&& ((Constant) ifTrue).getValue() == null
				&& ((Constant) ifFalse).getValue() == null)
			throw new IllegalOperandException();

		this.resultClass = Object.class;
		Class<?> ifTrueClass = ifTrue.getResultClass();
		Class<?> ifFalseClass = ifFalse.getResultClass();

		if (ifTrueClass.equals(ifFalseClass))
			resultClass = ifTrueClass;
		else if (ifTrue instanceof Constant
				&& ((Constant) ifTrue).getValue() == null)
			resultClass = ifFalseClass;
		else if (ifFalse instanceof Constant
				&& ((Constant) ifFalse).getValue() == null)
			resultClass = ifTrueClass;
		else if (Byte.class.isAssignableFrom(ifTrueClass)) {
			if (Integer.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Byte.class;
			} else if (Short.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Short.class;
			} else if (canPerformNumericPromotionForAll(ifTrueClass,
					ifFalseClass))
				resultClass = getNumericPromotion(ifTrueClass, ifFalseClass);
		} else if (Character.class.isAssignableFrom(ifTrueClass)) {
			if (Integer.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Character.class;
			} else if (canPerformNumericPromotionForAll(ifTrueClass,
					ifFalseClass))
				resultClass = getNumericPromotion(ifTrueClass, ifFalseClass);
		} else if (Short.class.isAssignableFrom(ifTrueClass)) {
			if (Integer.class.isAssignableFrom(ifFalseClass)
					|| Byte.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Short.class;
			} else if (canPerformNumericPromotionForAll(ifTrueClass,
					ifFalseClass))
				resultClass = getNumericPromotion(ifTrueClass, ifFalseClass);
		} else if (Integer.class.isAssignableFrom(ifTrueClass)) {
			if (Short.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Short.class;
			} else if (Byte.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Byte.class;
			} else if (Character.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Character.class;
			} else if (canPerformNumericPromotionForAll(ifTrueClass,
					ifFalseClass))
				resultClass = getNumericPromotion(ifTrueClass, ifFalseClass);
		} else if (canPerformNumericPromotionForAll(ifTrueClass, ifFalseClass))
			resultClass = getNumericPromotion(ifTrueClass, ifFalseClass);

		this.test = test;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
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
