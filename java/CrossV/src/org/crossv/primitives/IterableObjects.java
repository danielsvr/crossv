package org.crossv.primitives;

import java.util.Iterator;

public class IterableObjects<E> implements Iterable<E> {

	private Iterator<E> iterator;

	public IterableObjects(Iterator<E> iterator) {
		if (iterator == null)
			throw new ArgumentNullException("iterator");
		this.iterator = iterator;
	}

	@Override
	public Iterator<E> iterator() {
		return iterator;
	}

}
