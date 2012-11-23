package org.crossv.primitives;

import java.util.Iterator;

public class IterableObjects<E> implements Iterable<E> {

	private Iterator<E> iterator;
	private final IteratorFactory factory;

	public IterableObjects(Iterator<E> iterator) {
		if (iterator == null)
			throw new ArgumentNullException("iterator");
		this.iterator = iterator;
		this.factory = null;
	}

	public IterableObjects(IteratorFactory factory) {
		if (factory == null)
			throw new ArgumentNullException("factory");
		this.factory = factory;
		this.iterator = factory.create();
	}

	@Override
	public Iterator<E> iterator() {
		if (factory != null)
			iterator = factory.create();
		if (iterator instanceof Resettable)
			((Resettable) iterator).reset();

		return iterator;
	}
}
