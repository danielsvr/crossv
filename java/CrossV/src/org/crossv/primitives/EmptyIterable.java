package org.crossv.primitives;

import java.util.*;

public class EmptyIterable<E> implements Iterable<E> {
	public EmptyIterable() {
	}

	@Override
	public Iterator<E> iterator() {
		return new EmptyIterator();
	}

	private class EmptyIterator extends IteratorAdapter<E> {
		@Override
		public void reset() {
		}
	}
}
