package org.crossv.primitives.tests;

import static org.crossv.tests.helpers.Matchers.isSequence;
import static org.junit.Assert.assertThat;

import org.crossv.primitives.Iterables;
import org.crossv.primitives.Lists;
import org.crossv.primitives.Predicate;
import org.junit.Test;

public class IterablesTests {

	@Test
	public void where_10Elements_OnlyEvenNumbers_OddElementsAreExcluded() {
		Iterable<Integer> inputValues = Lists.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Predicate<Integer> areEven;
		areEven = new Predicate<Integer>() {
			@Override
			public boolean eval(Integer value) {
				return value % 2 == 0;
			}
		};
		Iterable<Integer> actual = Iterables.where(inputValues, areEven);
		assertThat(actual, isSequence(2, 4, 6, 8, 10));
	}

	@Test
	public void ofClass_5StringsAnd5Ints_ElementsOfStringSelected_IterableSizeIs5() {
		Iterable<Object> input = Lists.<Object> of(1, 2, "3", 4, "5", "6", 7,
				"8", "9", 10);
		Iterable<String> actual = Iterables.ofClass(String.class, input);
		assertThat(actual, isSequence("3", "5", "6", "8", "9"));
	}
}