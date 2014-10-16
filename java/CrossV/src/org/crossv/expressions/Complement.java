package org.crossv.expressions;

import static org.crossv.expressions.ExpressionClass.CByte;
import static org.crossv.expressions.ExpressionClass.CInteger;
import static org.crossv.expressions.ExpressionClass.CLong;
import static org.crossv.expressions.ExpressionClass.CShort;

public class Complement extends UnaryExpression {
	private Class<?> resultClass;

	public Complement(Expression operand) {
		super(operand);
		verifyOperand();
		if (operand.isAssignableTo(CLong))
			resultClass = CLong;
		else
			resultClass = CInteger;
	}

	private void verifyOperand() {
		if (!operand.isAssignableToAny(CByte, CShort, CInteger, CLong))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitComplement(this);
	}
}
