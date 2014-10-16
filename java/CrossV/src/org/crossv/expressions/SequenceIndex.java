package org.crossv.expressions;

import java.util.Enumeration;
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
		if (left.isAssignableTo(String.class))
			return Character.class;
		if(left.isArray())
			return transformToClassIfPrimitive(left.getResultClass().getComponentType());
		if (left.isAssignableToAny(Enumeration.class, Iterable.class))
			return Object.class;
		return null;
	}

	private void verifyIndex() {
		if (!right.isAssignableTo(Integer.class))
			throw illegalOperand();
	}

	private void verifySequence() {
		if (!left.isArray()
				&& !left.isAssignableToAny(String.class, Enumeration.class,
						Iterable.class))
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
