package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CString;
import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;

public class Add extends AdditiveExpression {
	private Class<?> resultClass;

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
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitAdd(this);
	}
}