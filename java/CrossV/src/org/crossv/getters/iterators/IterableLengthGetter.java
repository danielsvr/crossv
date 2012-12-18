package org.crossv.getters.iterators;

import org.crossv.getters.GetterValidationException;
import org.crossv.getters.LengthGetterDescriptor;
import org.crossv.primitives.Iterables;

@SuppressWarnings("rawtypes")
public class IterableLengthGetter<E> extends
		LengthGetterDescriptor<E, Iterable> {
	public IterableLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, Iterable.class, getterName);
	}

	@Override
	protected boolean canGetValue() throws GetterValidationException {
		return Iterable.class.isAssignableFrom(getReturnClass());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected int getLength(Iterable getterValue) {
		return Iterables.count(getterValue);
	}
}
