package org.crossv.primitives;

public class IsInstanceOf<E> implements Predicate<E> {
	private final Class<? extends E> clazz;

	public IsInstanceOf(Class<? extends E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean eval(E value) {
		if (value == null)
			return false;
		Class<?> valueClazz = value.getClass();
		return clazz.isAssignableFrom(valueClazz);
	}
	
}
