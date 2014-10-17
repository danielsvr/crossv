package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.canPromoteNumbers;

public abstract class EqualityExpression extends BinaryExpression {

	public EqualityExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!canPromoteNumbers(leftClass, rightClass))
			if (!left.isAssignableTo(right.getResultClass()))
				if (!right.isAssignableTo(left.getResultClass()))
					throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}
}
