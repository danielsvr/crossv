package org.crossv.primitives;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayIterable<E> implements Iterable<E> {

	E obj;
	E[] objs;
	boolean isArray;

	public ArrayIterable(E obj) {
		this(obj, null);
	}

	public ArrayIterable(E obj, E[] objs) {
		this(obj, objs, false);
	}

	public ArrayIterable(E obj, boolean isArray) {
		this(obj, null, isArray);
	}

	private ArrayIterable(E obj, E[] objs, boolean isArray) {
		this.obj = obj;
		this.objs = objs;
		this.isArray = isArray;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator(obj, objs, isArray);
	}

	private class ArrayIterator extends IteratorAdapter<E> {
		int index;
		E obj;
		E[] objs;
		private boolean isArray;

		public ArrayIterator(E obj, E[] objs, boolean isArray) {
			this.obj = obj;
			this.objs = objs;
			index = obj != null && !isArray ? -2 : -1;
			this.isArray = isArray;
		}

		@Override
		public boolean hasNext() {
			if (isArray) {
				return obj != null && index < Array.getLength(obj) - 1;
			}
			return (index < -1 && obj != null)
					|| (objs != null && index < objs.length - 1);
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (isArray) {
				return obj != null ? (E) Array.get(obj, ++index) : null;
			}
			if (++index == -1)
				return obj;
			return objs != null ? objs[index] : null;
		}
	}

}
