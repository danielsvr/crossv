package org.crossv.primitives;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {

	int index;
	E obj;
	E[] objs;

	public ArrayIterator(E obj, E[] objs) {
		this.obj = obj;
		this.objs = objs;
		index = obj != null ? -2 : -1;
	}

	@Override
	public boolean hasNext() {
		return (index < -1 && obj != null)
				|| (objs != null && index < objs.length - 1);
	}

	@Override
	public E next() {
		if (++index == -1)
			return obj;
		return objs != null ? objs[index] : null;
	}

	@Override
	public void remove() {
	}
}
