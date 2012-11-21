package org.crossv.primitives;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorAdapter<E> implements Iterator<E> {

	protected IteratorAdapter() {
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public E next() {
		throw new NoSuchElementException(
				"There are no more elements to iterate.");
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"Cannot remove elements from an empty iterator");
	}
}
