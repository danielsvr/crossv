package org.crossv.primitives;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterable<E> implements Iterable<E> {

	private Enumeration<E> enumeration;

	public EnumerationIterable(Enumeration<E> enumeration) {
		if (enumeration == null)
			throw new ArgumentNullException("enumeration");
		this.enumeration = enumeration;
	}

	@Override
	public Iterator<E> iterator() {
		return new EnumerationIterator(enumeration);
	}

	private class EnumerationIterator extends IteratorAdapter<E> {

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
}