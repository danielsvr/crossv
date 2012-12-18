package org.crossv.primitives;

import java.util.Iterator;

public class CreateItemIterable<E> implements Iterable<E> {
	private E value;
	private int max;

	public CreateItemIterable(E value, int max) {
		this.value = value;
		this.max = max;
	}

	@Override
	public Iterator<E> iterator() {
		return new CreateItemIterator();
	}

	private class CreateItemIterator extends IteratorAdapter<E> {

		private int itemsCount;

		public CreateItemIterator() {
			itemsCount = 0;
		}

		@Override
		public boolean hasNext() {
			return itemsCount < max;
		}

		@Override
		public E next() {
			itemsCount++;
			return value;
		}
	}
}
