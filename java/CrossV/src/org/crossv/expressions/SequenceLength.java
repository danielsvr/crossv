package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CEnumeration;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CIterable;
import static org.crossv.primitives.ClassDescriptor.CString;

public class SequenceLength extends UnaryExpression {
	private Class<?> resultClass;

	public SequenceLength(Expression operand) {
		super(operand);
		verifyOperand();
		resultClass = CInteger;
	}

	private void verifyOperand() {
		if (!operand.isAssignableToAny(CString, CIterable, CEnumeration)
				&& !operand.isArray())
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSequenceLength(this);
	}
}
