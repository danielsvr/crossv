package org.crossv.expressions;

import static org.crossv.expressions.ExpressionClass.CDouble;
import static org.crossv.expressions.ExpressionClass.CFloat;
import static org.crossv.expressions.ExpressionClass.CInteger;
import static org.crossv.expressions.ExpressionClass.CLong;
import static org.crossv.expressions.ExpressionClass.CNumber;

public abstract class ShiftExpression extends BinaryExpression {
	private Class<?> resultClass;

	public ShiftExpression(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		resultClass = left.isAssignableTo(CLong) ? CLong : CInteger;
	}

	private void verifyOperands() {
		if (left.isAssignableToAny(CDouble, CFloat)
				|| !left.isAssignableTo(CNumber)
				|| right.isAssignableToAny(CDouble, CFloat)
				|| !right.isAssignableTo(CNumber))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

}
