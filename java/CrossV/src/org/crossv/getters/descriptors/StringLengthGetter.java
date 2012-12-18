package org.crossv.getters.descriptors;

public class StringLengthGetter<E> extends LengthGetterDescriptor<E, String> {
	public StringLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, String.class, getterName);
	}

	@Override
	protected boolean canGetValue() {
		return getReturnClass().equals(String.class);
	}

	@Override
	protected int getLength(String getterValue) {
		return getterValue.length();
	}
}
