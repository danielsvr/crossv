package org.crossv.primitives;

public class LazyArrayConverterIterator<E, ER> extends IteratorAdapter<ER> {

	private final E[] array;
	private final Function<E, ER> converter;
	private int index;

	public LazyArrayConverterIterator(E[] array, Function<E, ER> converter) {
		this.array = array;
		this.converter = converter;
		this.index = -1;
	}

	@Override
	public boolean hasNext() {
		return ++index < array.length;
	}

	@Override
	public ER next() {
		return converter.eval(array[index]);
	}
}
