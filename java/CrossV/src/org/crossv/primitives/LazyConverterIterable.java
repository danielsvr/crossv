package org.crossv.primitives;

import java.util.Iterator;

public class LazyConverterIterable<E, ER> implements Iterable<ER> {
	private Function<E, ER> converter;
	private Iterable<E> iterable;

	public LazyConverterIterable(Iterable<E> iterable, Function<E, ER> converter) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (converter == null)
			throw new ArgumentNullException("converter");
		this.iterable = iterable;
		this.converter = converter;
	}

	@Override
	public Iterator<ER> iterator() {
		return new LazyConverterIterator(iterable.iterator(), converter);
	}

	private class LazyConverterIterator extends IteratorAdapter<ER> {
		private final Function<E, ER> converter;
		private Iterator<E> iterator;

		public LazyConverterIterator(Iterator<E> iterator,
				Function<E, ER> converter) {
			this.iterator = iterator;
			this.converter = converter;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public ER next() {
			return converter.eval(iterator.next());
		}
	}
}
