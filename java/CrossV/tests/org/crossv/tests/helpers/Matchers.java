package org.crossv.tests.helpers;

import org.crossv.ContextEvaluator;
import org.crossv.EvaluatorRegistry;
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

	public static <E> Matcher<Iterable<E>> hasAll(E... objs) {
		return new HasElementsMatcher<E>(Iterables.toIterable(objs));
	}

	public static <E> Matcher<Iterable<E>> doesntHave(E obj) {
		return org.hamcrest.CoreMatchers.not(has(obj));
	}

	public static <E> Matcher<Iterable<E>> doesntHaveAny(E... objs) {
		return org.hamcrest.CoreMatchers.not(hasAll(objs));
	}

	public static Matcher<EvaluatorRegistry> contains(ContextEvaluator<?,?> evaluator) {
		return new EvaluatorRegistryContainsMatcher(evaluator) ;
	}
}