package org.crossv.tests.helpers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class AssignableToMatcher extends BaseMatcher<Object> {

	private Class<?> clazz;

	public AssignableToMatcher(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean matches(Object arg0) {
		if (arg0 instanceof Class) {
			return clazz.isAssignableFrom((Class<?>) arg0);
		}
		return false;
	}

	@Override
	public void describeTo(Description arg0) {
	}
}
