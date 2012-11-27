package org.crossv.primitives;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class IteratorAdapter<E> implements Iterator<E> {

	protected IteratorAdapter() {
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public E next() {
		String message = "There are no more elements to iterate.";
		throw new NoSuchElementException(message);
	}

	@Override
	public void remove() {
		String message = "Cannot remove elements from a readonly iterator";
		throw new IllegalStateException(message);
	}
}
