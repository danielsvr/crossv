package org.crossv.primitives;

import java.util.Iterator;

public class LazyConverterIterator<E, ER> extends IteratorAdapter<ER> {

	private Iterator<E> iterator;
	private final Function<E, ER> converter;
	private final Iterable<E> iterable;

	public LazyConverterIterator(Iterable<E> iterable, Function<E, ER> converter) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (converter == null)
			throw new ArgumentNullException("converter");
		this.iterable = iterable;
		this.iterator = iterable.iterator();
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

	@Override
	public void reset() {
		iterator = iterable.iterator();
	}
}
