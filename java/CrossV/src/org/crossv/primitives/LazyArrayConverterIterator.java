package org.crossv.primitives;

import java.util.Iterator;

public class LazyArrayConverterIterator<E, ER> implements Iterator<ER> {

	private final E[] array;
	private final Function<E, ER> converter;
	private int index;

	public LazyArrayConverterIterator(E[] array, Function<E, ER> converter) {
		this.array = array;
		this.converter = converter;
		this.index = -1;
	}

	@Override
	public boolean hasNext() {
		return ++index < array.length;
	}

	@Override
	public ER next() {
		return converter.eval(array[index]);
	}

	@Override
	public void remove() {
		throw new IllegalStateException("Cannot remove elements.");
	}

}
