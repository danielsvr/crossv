package org.crossv.primitives.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.primitives.ClassDescriptor;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ClassDescriptorTests {
	@Test
	public void executeParameterlessVoidMethod_returnsNull() throws Exception {
		try {
			ClassDescriptor descriptor;
			Object result;
			Monkey monkey = TestObjectFactory.createMonkey();
			
			descriptor = new ClassDescriptor(Monkey.class);
			result = descriptor.execute(monkey, "setName", "name");
			assertThat(result, is(equalTo(null)));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test(expected = NoSuchMethodException.class)
	public void executeUndefinedMethod_throwsNoSuchMethodException()
			throws Exception {
		ClassDescriptor descriptor;

		descriptor = new ClassDescriptor(String.class);
		descriptor.execute("test", "blah");
	}

	@Test
	public void executeEqualsOnStringPassingInt_returnsBoolean()
			throws Exception {
		ClassDescriptor descriptor;
		Object result;

		descriptor = new ClassDescriptor(String.class);
		result = descriptor.execute("test", "equals", 123);

		assertThat(result, is(Boolean.class));
	}

	@Test
	public void executeEqualsOnStringPassingInt_returnsFalse() throws Exception {
		ClassDescriptor descriptor;
		Object result;

		descriptor = new ClassDescriptor(String.class);
		result = descriptor.execute("test", "equals", 123);

		assertThat(((Boolean) result).booleanValue(), is(equalTo(false)));
	}
}
