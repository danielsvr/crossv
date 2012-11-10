package org.crossv.tests.helpers;

import org.crossv.primitives.Iterables;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

public class HasElementsMatcher<E> extends TypeSafeMatcher<Iterable<E>> {

	private Iterable<E> objs;

	public HasElementsMatcher(Iterable<E> objs){
		this.objs = objs;
	}
	@Override
	public void describeTo(Description description) {
		String message = "Iterable contain all the elements";
		description.appendText(message);
	}

	@Override
	public boolean matchesSafely(Iterable<E> obj) {
		return Iterables.containsAll(obj, objs);
	}

}
