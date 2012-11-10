package org.crossv.primitives;

import java.util.*;

public class IterableOnly<E> implements Iterable<E> {

	private final Iterable<E> iterable;

	public IterableOnly(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		this.iterable = iterable;
	}

	public IterableOnly() {
		this.iterable = new ArrayList<E>();
	}

	@Override
	public Iterator<E> iterator() {
		return iterable.iterator();
	}

	public List<E> toList() {
		return Iterables.toList(this);
	}

	public Iterable<E> innerIterable() {
		return iterable;
	}
}
