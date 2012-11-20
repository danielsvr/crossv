package org.crossv.primitives;

import java.util.Iterator;

final class LazyConvertorIterable<ER, E> implements Iterable<ER> {

	private final Iterable<E> iterable;
	private final Function<E, ER> converter;
	private E[] array;

	public LazyConvertorIterable(Iterable<E> iterable, Function<E, ER> converter) {
		this.iterable = iterable;
		this.converter = converter;
		array = null;
	}

	public LazyConvertorIterable(E[] array, Function<E, ER> converter) {
		this.array = array;
		this.converter = converter;
		iterable = null;
	}

	@Override
	public Iterator<ER> iterator() {
		if (iterable != null)
			return new LazyConverterIterator<E, ER>(iterable.iterator(),
					converter);
		if (array != null)
			return new LazyArrayConverterIterator<E, ER>(array, converter);
		
		return new Iterator<ER>(){
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public ER next() {
				return null;
			}

			@Override
			public void remove() {
			}
		};
	}
}