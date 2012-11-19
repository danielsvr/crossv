package org.crossv.primitives;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterable<E> implements Iterable<E> {

	private Enumeration<E> enumeration;

	public EnumerationIterable(Enumeration<E> enumeration) {
		this.enumeration = enumeration;
	}

	@Override
	public Iterator<E> iterator() {
		return new EnumerationIterator<E>(enumeration);
	}

}
