package org.crossv.tests.helpers;

import java.util.List;

import org.crossv.primitives.*;
import org.hamcrest.*;
import org.junit.internal.matchers.*;

public class IsSizeMatcher<E extends Iterable<?>> extends TypeSafeMatcher<E> {
	private final Number size;

	public IsSizeMatcher(Number size) {
		this.size = size;
	}

	@Override
	public boolean matchesSafely(E obj) {
		Iterable<?> iterable = (Iterable<?>) obj;
		List<?> list = Iterables.toList(iterable);
		int listSize = list.size();
		return size.equals(listSize);
	}

	public void describeTo(Description description) {
		String message = "Iterable with size " + size;
		description.appendText(message);
	}
}