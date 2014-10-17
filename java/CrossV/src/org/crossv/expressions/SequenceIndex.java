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
	private Expression seq;
	private Expression idx;

	public SequenceIndex(Expression sequence, Expression index) {
		if (sequence == null)
			throw new ArgumentNullException("sequence");
		if (index == null)
			throw new ArgumentNullException("index");
		this.seq = sequence;
		this.idx = index;
		verifySequence();
		verifyIndex();
		resultClass = calculateResultClass();
	}

	private Class<?> calculateResultClass() {
		if (seq.isAssignableTo(CString))
			return CCharacter;
		if (seq.isArray()) {
			Class<?> clazz = seq.getResultClass();
			clazz = clazz.getComponentType();
			clazz = transformToClassIfPrimitive(clazz);
			return clazz;
		}
		if (seq.isAssignableToAny(CEnumeration, CIterable))
			return CObject;
		return null;
	}

	private void verifyIndex() {
		if (!idx.isAssignableTo(CInteger))
			throw illegalOperand();
	}

	private void verifySequence() {
		if (!seq.isArray()
				&& !seq.isAssignableToAny(CString, CEnumeration, CIterable))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	public Expression getSequence() {
		return seq;
	}

	public Expression getIndex() {
		return idx;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitSequenceIndex(this);
	}
}
