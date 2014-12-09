package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CRuntimeObject;
import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;

public abstract class EqualityExpression extends BinaryExpression {

	public EqualityExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!left.isAssignableTo(CRuntimeObject)
				&& !right.isAssignableTo(CRuntimeObject)
				&& !canPromoteNumbers(leftClass, rightClass)
				&& !left.isAssignableTo(right.getResultClass())
				&& !right.isAssignableTo(left.getResultClass()))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}
}
