package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CCharacter;
import static org.crossv.primitives.ClassDescriptor.CEnumeration;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CIterable;
import static org.crossv.primitives.ClassDescriptor.CObject;
import static org.crossv.primitives.ClassDescriptor.CString;
import static org.crossv.primitives.ClassDescriptor.transformToClassIfPrimitive;

import org.crossv.primitives.ArgumentNullException;

public class SequenceIndex extends Expression {
	private Class<?> resultClass;
	private Expression sequence;
	private Expression index;

	public SequenceIndex(Expression sequence, Expression index) {
		if (sequence == null)
			throw new ArgumentNullException("sequence");
		if (index == null)
			throw new ArgumentNullException("index");
		this.sequence = sequence;
		this.index = index;
		verifySequence();
		verifyIndex();
		resultClass = calculateResultClass();
	}

	private Class<?> calculateResultClass() {
		if (sequence.isAssignableTo(CString))
			return CCharacter;
		if (sequence.isArray()) {
			Class<?> clazz = sequence.getResultClass();
			clazz = clazz.getComponentType();
			clazz = transformToClassIfPrimitive(clazz);
			return clazz;
		}
		// CEnumeration, CIterable
		return CObject;
	}

	private void verifyIndex() {
		if (!index.isAssignableTo(CInteger))
			throw illegalOperand();
	}

	private void verifySequence() {
		if (!sequence.isArray() && !isSequenceAnIndexableClass())
			throw illegalOperand();
	}

	private boolean isSequenceAnIndexableClass() {
		return sequence.isAssignableToAny(CString, CEnumeration, CIterable);
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	public Expression getSequence() {
		return sequence;
	}

	public Expression getIndex() {
		return index;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSequenceIndex(this);
	}
}
