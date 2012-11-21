package org.crossv.primitives;

import java.util.Iterator;

public class LazyCastIterator<E, ER> extends IteratorAdapter<ER> {

	private final Iterator<E> iterator;

	public LazyCastIterator(Iterator<E> iterator) {
		this.iterator = iterator;
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
}
