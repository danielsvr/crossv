package org.crossv.getters.descriptors;

import java.lang.reflect.Array;

public class ArrayLengthGetter<E> extends LengthGetterDescriptor<E, Object> {
	public ArrayLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, Object.class, getterName);
	}

	@Override
	protected boolean canGetValue() {
		return getReturnClass().isArray();
	}

	@Override
	protected int getLength(Object getterValue) {
		return Array.getLength(getterValue);
	}
}
