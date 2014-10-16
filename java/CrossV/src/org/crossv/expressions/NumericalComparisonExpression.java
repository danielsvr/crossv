package org.crossv.expressions;

import static org.crossv.expressions.ExpressionClass.CBoolean;
import static org.crossv.primitives.ExpressionUtil.canPromoteNumbers;

public abstract class NumericalComparisonExpression extends
		RelationalExpression {

	public NumericalComparisonExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (!canPromoteNumbers(leftClass, rightClass))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}
}
