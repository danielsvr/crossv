package org.crossv.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class IteratorTests {

	@Test
	public void testname() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator<Integer> iterator1 = list.iterator();
		Assert.assertThat(iterator1.next(), CoreMatchers.is(1));
		Iterator<Integer> iterator2 = list.iterator();
		Assert.assertThat(iterator2.next(), CoreMatchers.is(1));
	}
}
