package org.crossv.primitives;

import java.util.Iterator;

public class WhereIterator<E> extends IteratorAdapter<E> {

	private Iterator<E> iterator;
	private Predicate<E> predicate;
	private boolean iterationStarted;
	private E current;
	private boolean iterationEnded;

	public WhereIterator(Iterator<E> iterator, Predicate<E> predicate) {
		if (iterator == null)
			throw new ArgumentNullException("iterator");
		if (predicate == null)
			throw new ArgumentNullException("predicate");
		this.iterator = iterator;
		this.predicate = predicate;
		this.iterationStarted = false;
	}

	@Override
	public boolean hasNext() {
		current = findNext();
		return !iterationEnded;
	}

	@Override
	public E next() {
		if (!iterationStarted)
			current = findNext();
		if (!iterationEnded)
			return current;

		return super.next();
	}

	private E findNext() {
		iterationStarted = true;
		while (iterator.hasNext()) {
			E next = iterator.next();
			if (predicate.eval(next))
				return next;
		}
		iterationEnded = true;
		return null;
	}
}
