package org.crossv.primitives;

import java.util.Iterator;

public class LazyArrayConverterIterable<E, ER> implements Iterable<ER> {
	private final E[] array;
	private final Function<E, ER> converter;

	public LazyArrayConverterIterable(E[] array, Function<E, ER> converter) {
		if (array == null)
			throw new ArgumentNullException("array");
		if (converter == null)
			throw new ArgumentNullException("converter");
		this.array = array;
		this.converter = converter;
	}

	@Override
	public Iterator<ER> iterator() {
		return new LazyArrayConverterIterator(array, converter);
	}

	private class LazyArrayConverterIterator extends IteratorAdapter<ER> {
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
	}
}
