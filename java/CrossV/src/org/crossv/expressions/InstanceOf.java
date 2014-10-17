package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CClass;

public class InstanceOf extends RelationalExpression {
	public InstanceOf(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (left.returnsPrimitiveType() || !right.isAssignableTo(CClass))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitInstanceOf(this);
	}

}
