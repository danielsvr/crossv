package org.crossv.primitives;

import java.util.Iterator;

public class LazyCastIterator<E, ER> extends IteratorAdapter<ER> {

	private Iterator<E> iterator;
	private final Iterable<E> iterable;

	public LazyCastIterator(Iterable<E> iterable) {
		this.iterable = iterable;
		this.iterator = iterable.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ER next() {
		return (ER) iterator.next();
	}

	@Override
	public void reset() {
		iterator = iterable.iterator();
	}
}
