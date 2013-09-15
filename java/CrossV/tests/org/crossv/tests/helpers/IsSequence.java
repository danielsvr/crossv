package org.crossv.tests.helpers;

import java.util.List;

import org.crossv.primitives.Iterables;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsSequence<E> extends TypeSafeMatcher<E> {

	private Object[] objects;

	public IsSequence(Object[] objects) {
		this.objects = objects;
	}

	@Override
	public void describeTo(Description arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean matchesSafely(E obj) {
		List<?> iterable = Iterables.toList((Iterable<?>) obj);

		if (iterable.size() != objects.length)
			return false;

		for (int i = 0; i < iterable.size(); i++) {
			if (!equals(iterable.get(i), objects[i]))
				return false;
		}
		return true;
	}

	private boolean equals(Object left, Object right) {
		return (left == null && right == null)
				|| (left != null && left.equals(right));
	}

}
