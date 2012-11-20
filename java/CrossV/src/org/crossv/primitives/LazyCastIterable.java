package org.crossv.primitives;

import java.util.Iterator;

final class LazyCastIterable<E, ER> implements Iterable<ER> {

	private final Iterable<E> iterable;

	public LazyCastIterable(Iterable<E> iterable) {
		this.iterable = iterable;
	}

	@Override
	public Iterator<ER> iterator() {
		return new LazyCastIterator<E, ER>(iterable.iterator());
	}
}