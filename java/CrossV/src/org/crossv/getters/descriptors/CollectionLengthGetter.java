package org.crossv.getters.descriptors;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class CollectionLengthGetter<E> extends
		LengthGetterDescriptor<E, Collection> {

	public CollectionLengthGetter(Class<E> scopeClass, String getterName) {
		super(scopeClass, Collection.class, getterName);
	}

	@Override
	protected boolean canGetValue() {
		return Collection.class.isAssignableFrom(getReturnClass());
	}

	@Override
	protected int getLength(Collection getterValue) {
		return getterValue.size();
	}
}
