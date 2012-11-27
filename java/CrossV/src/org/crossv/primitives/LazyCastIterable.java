package org.crossv.primitives;

import java.util.Iterator;

public class LazyCastIterable<E, ER> implements Iterable<ER> {

	private final Iterable<E> iterable;

	public LazyCastIterable(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		this.iterable = iterable;
	}

	@Override
	public Iterator<ER> iterator() {
		return new LazyCastIterator(iterable.iterator());
	}

	private class LazyCastIterator extends IteratorAdapter<ER> {

		private Iterator<E> iterator;

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
}