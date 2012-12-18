package org.crossv.getters.descriptors;

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
		String message;

		Object getterValue = super.getValue(obj);
		if (canGetValue())
			return getLength(getterClass.cast(getterValue));

		message = "Could not apply lenght evaluation on {0}";
		message = MessageFormat.format(message, getterClass.getSimpleName());
		throw new MalformedMemberException(message);
	}

	@Override
	public Class<?> getReturnClass() {
		return getterClass;
	}

	protected abstract boolean canGetValue();

	protected abstract int getLength(EGetter getterValue);
}
