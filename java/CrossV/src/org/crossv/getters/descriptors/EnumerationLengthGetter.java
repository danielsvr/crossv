package org.crossv.getters.descriptors;

import java.util.Enumeration;

import org.crossv.primitives.Iterables;

@SuppressWarnings("rawtypes")
public class EnumerationLengthGetter<E> extends
		LengthGetterDescriptor<E, Enumeration> {

	public EnumerationLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, Enumeration.class, getterName);
	}

	@Override
	protected boolean canGetValue() {
		return Enumeration.class.isAssignableFrom(getReturnClass());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected int getLength(Enumeration getterValue) {
		return Iterables.count(getterValue);
	}
}
