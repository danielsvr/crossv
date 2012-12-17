package org.crossv.primitives;

import java.util.Enumeration;
import java.util.Iterator;

public class IterableToEnumeration<E> implements Enumeration<E> {
	
	private Iterator<E> iterator;

	public IterableToEnumeration(Iterable<E> iterable) {
		this.iterator = iterable.iterator();
	}

	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public E nextElement() {
		return iterator.next();
	}

}
