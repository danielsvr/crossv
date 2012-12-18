package org.crossv.getters.collections;

import java.util.Collection;

import org.crossv.getters.GetterValidationException;
import org.crossv.getters.LengthGetterDescriptor;

@SuppressWarnings("rawtypes")
public class CollectionLengthGetter<E> extends
		LengthGetterDescriptor<E, Collection> {

	public CollectionLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, Collection.class, getterName);
	}

	@Override
	protected boolean canGetValue() throws GetterValidationException {
		return Collection.class.isAssignableFrom(getReturnClass());
	}

	@Override
	protected int getLength(Collection getterValue) {
		return getterValue.size();
	}
}
