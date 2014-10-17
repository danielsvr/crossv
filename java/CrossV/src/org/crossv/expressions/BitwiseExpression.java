package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CLong;
import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;

public abstract class BitwiseExpression extends BinaryExpression {
	private Class<?> resultClass;

	public BitwiseExpression(Expression left, Expression right) {
		super(left, right);
		resultClass = calculateResultClass();
		verifyResultClass();
	}

	private void verifyResultClass() {
		if (resultClass == null || !CInteger.isAssignableFrom(resultClass)
				&& !CLong.isAssignableFrom(resultClass)
				&& !CBoolean.isAssignableFrom(resultClass))
			throw illegalOperand();
	}

	private Class<?> calculateResultClass() {
		if (left.isAssignableTo(CBoolean) && right.isAssignableTo(CBoolean))
			return CBoolean;
		else if (canPromoteNumbers(leftClass, rightClass))
			return getNumericPromotion(leftClass, rightClass);
		return null;
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}
}
