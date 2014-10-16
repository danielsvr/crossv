package org.crossv.expressions;

import static org.crossv.expressions.ExpressionClass.CCharacter;
import static org.crossv.expressions.ExpressionClass.CEnumeration;
import static org.crossv.expressions.ExpressionClass.CInteger;
import static org.crossv.expressions.ExpressionClass.CIterable;
import static org.crossv.expressions.ExpressionClass.CObject;
import static org.crossv.expressions.ExpressionClass.CString;
import static org.crossv.primitives.ClassDescriptor.transformToClassIfPrimitive;

public class SequenceIndex extends BinaryExpression {

	private Class<?> resultClass;

	public SequenceIndex(Expression left, Expression right) {
		super(left, right);
		verifySequence();
		verifyIndex();
		resultClass = calculateResultClass();
	}

	private Class<?> calculateResultClass() {
		if (left.isAssignableTo(CString))
			return CCharacter;
		if (left.isArray())
			return transformToClassIfPrimitive(left.getResultClass()
					.getComponentType());
		if (left.isAssignableToAny(CEnumeration, CIterable))
			return CObject;
		return null;
	}

	private void verifyIndex() {
		if (!right.isAssignableTo(CInteger))
			throw illegalOperand();
	}

	private void verifySequence() {
		if (!left.isArray()
				&& !left.isAssignableToAny(CString, CEnumeration, CIterable))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSequenceIndex(this);
	}
}
