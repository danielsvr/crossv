package org.crossv.getters;

import java.text.MessageFormat;


public abstract class LengthGetterDescriptor<E, EGetter> extends
		GetterDescriptor<E> {
	private Class<EGetter> getterClass;

	public LengthGetterDescriptor(Class<E> scopeClass,
			Class<EGetter> getterClass, String getterName) {
		super(scopeClass, getterName);
		this.getterClass = getterClass;
	}

	@Override
	public final Object getValue(E obj) throws Exception {
		Object getterValue = super.getValue(obj);
		if (canGetValue())
			return getLength(getterClass.cast(getterValue));
		String message;
		message = "Could not get lenght from an instance of {0}";
		message = MessageFormat.format(message, getterClass.getSimpleName());
		throw new MalformedMemberException(message);
	}

	@Override
	public Class<?> getReturnClass() throws GetterValidationException {
		if (getterClass.isAssignableFrom(super.getReturnClass()))
			return getterClass;
		String message;
		message = "The getter for \"{0}\" does not return expected type ({1})";
		message = MessageFormat.format(message, getName(),
				getterClass.getSimpleName());
		throw new NoSuchMemberException(message);
	}

	protected abstract boolean canGetValue() throws GetterValidationException;

	protected abstract int getLength(EGetter getterValue);
}
