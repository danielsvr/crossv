package org.crossv.tests.helpers;

import org.crossv.Evaluation;
import org.crossv.primitives.Action;
import org.crossv.primitives.Iterables;
import org.hamcrest.Matcher;

public class Matchers {

	public static <E extends Iterable<?>> Matcher<E> isEmpty() {
		return new IsEmptyMatcher<E>();
	}

	public static <E extends Iterable<?>> Matcher<E> hasSize(int size) {
		return new HasSizeMatcher<E>(size);
	}

	public static <E> Matcher<Iterable<E>> has(E obj) {
		return new HasElementsMatcher<E>(Iterables.toIterable(obj));
	}

	public static <E> Matcher<Iterable<E>> have(E obj) {
		return new HasElementsMatcher<E>(Iterables.toIterable(obj));
	}

	public static <E> Matcher<Iterable<E>> doesntHave(E obj) {
		return org.hamcrest.CoreMatchers.not(has(obj));
	}

	public static Matcher<Iterable<Evaluation>> doesntHaveFaultsWith(
			Exception exception) {
		return org.hamcrest.CoreMatchers.not(new HasFaultsWith(exception));
	}

	public static Matcher<Iterable<Evaluation>> haveFaultsWith(
			Exception exception) {
		return new HasFaultsWith(exception);
	}

	public static Matcher<Object> equalTo(Object obj) {
		return org.hamcrest.CoreMatchers.equalTo(obj);
	}

	public static Matcher<Object> assignableTo(Class<?> clazz) {
		return new AssignableToMatcher(clazz);
	}

	public static <E extends Iterable<?>> Matcher<E> isSequence(
			Object... objects) {
		return new IsSequence<E>(objects);
	}
	

	public static Matcher<Action> throwing(Class<? extends Throwable> expected) {
		return new ThrowingException(expected);
	}
}