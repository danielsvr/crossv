package org.crossv.primitives;

import java.util.Iterator;

public class LazyConverterIterator<E, ER> implements Iterator<ER> {

	private final Iterator<E> iterator;
	private final Function<E, ER> converter;

	public LazyConverterIterator(Iterator<E> iterator, Function<E, ER> converter) {
		if (iterator == null)
			throw new ArgumentNullException("iterator");
		if (converter == null)
			throw new ArgumentNullException("converter");
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

	@Override
	public void remove() {
		throw new IllegalStateException("Cannot remove elements.");
	}

}
