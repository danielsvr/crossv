package org.crossv.getters.strings;

import org.crossv.getters.GetterValidationException;
import org.crossv.getters.LengthGetterDescriptor;

public class StringLengthGetter<E> extends LengthGetterDescriptor<E, String> {
	public StringLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, String.class, getterName);
	}

	@Override
	protected boolean canGetValue() throws GetterValidationException {
		return getReturnClass().equals(String.class);
	}

	@Override
	protected int getLength(String getterValue) {
		return getterValue != null ? getterValue.length() : 0;
	}
}
