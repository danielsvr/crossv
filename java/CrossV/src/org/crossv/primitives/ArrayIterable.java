package org.crossv.primitives;

import java.util.Iterator;

public class ArrayIterable<E> implements Iterable<E> {
	private E obj;
	private E[] objs;

	public ArrayIterable(E obj, E[] objs) {
		this.obj = obj;
		this.objs = objs;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator<E>(obj, objs);
	}
}
