package org.crossv.primitives;

import java.util.Iterator;

public interface IteratorFactory{
	<E> Iterator<E> create();
}
