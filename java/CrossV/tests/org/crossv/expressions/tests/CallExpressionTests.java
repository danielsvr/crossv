package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.call;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.equal;
import static org.crossv.tests.helpers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.primitives.Action;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class CallExpressionTests {

	@Test
	public void createCall_WithNullInstanceAndSomeMethod_ThrowsIllegalArgumentException() {
		assertThat(new Action() {
			public void run() throws Exception {
				call(null, Monkey.class.getMethod("getName"));
			}
		}, is(throwing(IllegalArgumentException.class)));
	}

	@Test
	public void createCall_WithSomeInstanceAndNullMethod_ThrowsIllegalArgumentException() {
		assertThat(new Action() {
			public void run() throws Exception {
				call(constant("instance"), (Method) null);
			}
		}, is(throwing(IllegalArgumentException.class)));
	}

	@Test
	public void createCallGetRelativesAsListMethodOfMonkeyClassExpressionForString_ThrowsIllegalOperandException() {
		assertThat(new Action() {
			public void run() throws Exception {
				call(constant("123"), Monkey.class.getMethod("getRelativesAsList"));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	@Test
	public void createCallSetNameExpressionForMonkey_ThrowsIllegalOperandException() {
		final Monkey monkney = TestObjectFactory.createMonkey();

		assertThat(new Action() {
			public void run() throws Exception {
				call(constant(monkney), Monkey.class.getMethod("setName", String.class));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	@Test
	public void createCallSetNameExpressionForMonkeyOnOnjectConstant_ThrowsIllegalOperandException() {
		final Object obj = new Object();

		assertThat(new Action() {
			public void run() throws Exception {
				call(constant(obj), Monkey.class.getMethod("setName", String.class));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	// TODO create tests for parsing text 
	@Test
	public void createCallHashCodeExpressionForString_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call("123", "hashCode");
		assertThat(e.toString(), is("\"123\".hashCode()"));
	}

	@Test
	public void createCallEqualsExpressionForString_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call("123", "equals", 321);
		assertThat(e.toString(), is("\"123\".equals(321)"));
	}

	@Test
	public void createCallMonkeyGetNameExpressionForInstnace_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call(instance(),Monkey.class.getMethod("getName"));
		assertThat(e.toString(), is("obj.getName()"));
	}

	@Test
	public void createCallGetNameExpressionForMonkeyContext_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = equal(call(context(Monkey.class), "getName"), "name");
		assertThat(e.toString(), is("context.getName() == \"name\""));
	}

	@Test
	public void evaluateCallExpression_GetNameOfMonkeyConstant_ReturnsName()
			throws Exception {
		Monkey monkey = TestObjectFactory.createMonkey();
		monkey.setName("John");
		Expression e = call(constant(monkey), "getName");
		assertThat(e.evaluate(), is(equalTo("John")));
	}

	@Test
	public void evaluateCallExpression_GetRelativeOfMonkeyConstantByIndex_ReturnsTheRelative()
			throws Exception {
		Monkey expectedRelative = TestObjectFactory.createMonkey();
		Monkey monkey = TestObjectFactory.createMonkey();
		monkey.setRelativesAsArray(new Monkey[] { expectedRelative });
		Expression e = call(constant(monkey), "getRelativeByIndex", 0);
		assertThat(e.evaluate(), is(equalTo(expectedRelative)));
	}
}
