package org.crossv.primitives;

import java.util.Arrays;
import java.util.List;

public final class Lists {
	public static <E> List<E> of(E... items) {
		return Arrays.asList(items);
	}
}
