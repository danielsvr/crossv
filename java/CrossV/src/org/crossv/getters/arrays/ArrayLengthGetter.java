package org.crossv.getters.arrays;

import java.lang.reflect.Array;

import org.crossv.getters.LengthGetterDescriptor;

public class ArrayLengthGetter<E> extends LengthGetterDescriptor<E, Object> {
	public ArrayLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, Object.class, getterName);
	}

	@Override
	protected boolean canGetValue() {
		return true;
	}

	@Override
	protected int getLength(Object getterValue) {
		if (getterValue != null && getterValue.getClass().isArray())
			return Array.getLength(getterValue);
		return 0;
	}
}
