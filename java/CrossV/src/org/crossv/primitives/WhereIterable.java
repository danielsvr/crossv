package org.crossv.primitives;

import java.util.Iterator;

public class WhereIterable<E> implements Iterable<E> {

	private Iterable<E> iterable;
	private Predicate<E> predicate;

	public WhereIterable(Iterable<E> iterable, Predicate<E> predicate) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (predicate == null)
			throw new ArgumentNullException("predicate");
		this.iterable = iterable;
		this.predicate = predicate;
	}

	@Override
	public Iterator<E> iterator() {
		return new WhereIterator<E>(iterable.iterator(), predicate);
	}

}
