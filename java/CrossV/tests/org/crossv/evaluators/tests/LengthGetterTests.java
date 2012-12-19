package org.crossv.evaluators.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.crossv.getters.LengthGetterDescriptor;
import org.crossv.getters.NoSuchMemberException;
import org.crossv.getters.arrays.ArrayLengthGetter;
import org.crossv.getters.collections.CollectionLengthGetter;
import org.crossv.getters.enumerations.EnumerationLengthGetter;
import org.crossv.getters.iterators.IterableLengthGetter;
import org.crossv.getters.strings.StringLengthGetter;
import org.crossv.primitives.Iterables;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class LengthGetterTests {
	LengthGetterDescriptor<Monkey, ?> descriptor;
	Object length;

	@Test
	public void getValue_OfAnEmptyNameProperty_ZeroIsReturned()
			throws Exception {
		Monkey monkey;

		descriptor = new StringLengthGetter<Monkey>(Monkey.class, "Name");
		monkey = new Monkey();

		length = descriptor.getValue(monkey);
		assertThat(length, is((Object) 0));
	}

	@Test
	public void getValue_Of11CharsNameProperty_11IsReturned() throws Exception {
		Monkey monkey;

		descriptor = new StringLengthGetter<Monkey>(Monkey.class, "Name");
		monkey = new Monkey();
		monkey.setName("12345678901");
		length = descriptor.getValue(monkey);

		assertThat(length, is((Object) 11));
	}

	@Test
	public void getValue_Of11CharsNicknameFieldIsReturned() throws Exception {
		Monkey monkey;

		descriptor = new StringLengthGetter<Monkey>(Monkey.class, "Nickname");

		monkey = new Monkey();
		monkey.nickname = "12345678901";
		length = descriptor.getValue(monkey);

		assertThat(length, is((Object) 11));
	}
	
	@Test
	public void getValue_OfAPropertyThatDoesNotHaveLegth_NoSuchMemberExceptionIsThrown()
			throws Exception {
		Monkey monkey;

		descriptor = new StringLengthGetter<Monkey>(Monkey.class, "Mother");
		monkey = new Monkey();
		try {
			descriptor.getValue(monkey);
			fail("we expect an NoSuchMemberException exception");
		} catch (NoSuchMemberException e) {
		}
	}

	@Test
	public void getValue_Of11RelativesAsArrayProperty_11IsReturned()
			throws Exception {
		Monkey monkey;

		descriptor = new ArrayLengthGetter<Monkey>(Monkey.class,
				"RelativesAsArray");
		monkey = new Monkey();
		monkey.setRelativesAsArray(new Monkey[11]);
		length = descriptor.getValue(monkey);

		assertThat(length, is((Object) 11));
	}

	@Test
	public void getValue_Of11RelativesAsListProperty_11IsReturned()
			throws Exception {
		Monkey monkey;
		Iterable<Monkey> monkeys;

		descriptor = new CollectionLengthGetter<Monkey>(Monkey.class,
				"RelativesAsList");
		monkeys = Iterables.repeatDefault(11);
		monkey = new Monkey();
		monkey.setRelativesAsList(Iterables.toList(monkeys));
		length = descriptor.getValue(monkey);

		assertThat(length, is((Object) 11));
	}

	@Test
	public void getValue_Of11RelativesAsIterableProperty_11IsReturned()
			throws Exception {
		Monkey monkey;
		Iterable<Monkey> monkeys;

		descriptor = new IterableLengthGetter<Monkey>(Monkey.class,
				"RelativesAsIterable");

		monkeys = Iterables.repeatDefault(11);
		monkey = new Monkey();
		monkey.setRelativesAsIterable(monkeys);
		length = descriptor.getValue(monkey);

		assertThat(length, is((Object) 11));
	}

	@Test
	public void getValue_Of11RelativesAsEnumerationProperty_11IsReturned()
			throws Exception {
		Monkey monkey;
		Iterable<Monkey> monkeys;

		descriptor = new EnumerationLengthGetter<Monkey>(Monkey.class,
				"RelativesAsEnumeration");
		monkeys = Iterables.repeatDefault(11);
		monkey = new Monkey();
		monkey.setRelativesAsEnumeration(Iterables.toEnumeration(monkeys));
		length = descriptor.getValue(monkey);

		assertThat(length, is((Object) 11));
	}
}
