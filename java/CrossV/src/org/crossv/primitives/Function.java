package org.crossv.primitives;

public interface Function<E, ER> {
	
	ER eval(E value);
	
}
