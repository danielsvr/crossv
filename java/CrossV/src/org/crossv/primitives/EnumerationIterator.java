package org.crossv.primitives;

import java.util.Enumeration;

public class EnumerationIterator<E> extends IteratorAdapter<E> {

	private Enumeration<E> enumeration;

	public EnumerationIterator(Enumeration<E> enumeration) {
		this.enumeration = enumeration;
	}

	@Override
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	@Override
	public E next() {
		return enumeration.nextElement();
	}
}
