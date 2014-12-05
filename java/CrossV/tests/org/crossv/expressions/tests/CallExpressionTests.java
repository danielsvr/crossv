package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.call;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.equal;
import static org.crossv.tests.helpers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.crossv.primitives.Iterables.single;

import java.lang.reflect.Method;

import org.crossv.expressions.Call;
import org.crossv.expressions.Constant;
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
				call(constant("123"),
						Monkey.class.getMethod("getRelativesAsList"));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	@Test
	public void createCallSetNameExpressionForMonkey_ThrowsIllegalOperandException() {
		final Monkey monkney = TestObjectFactory.createMonkey();

		assertThat(new Action() {
			public void run() throws Exception {
				call(constant(monkney),
						Monkey.class.getMethod("setName", String.class));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	@Test
	public void createCallSetNameExpressionForMonkeyOnOnjectConstant_ThrowsIllegalOperandException() {
		final Object obj = new Object();

		assertThat(new Action() {
			public void run() throws Exception {
				call(constant(obj),
						Monkey.class.getMethod("setName", String.class));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	// TODO create tests for parsing call expression text
	@Test
	public void createCallHashCodeExpressionForString_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call(constant("123"), "hashCode");
		assertThat(e.toString(), is("\"123\".hashCode()"));
	}

	@Test
	public void createCallEqualsExpressionForString_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call(constant("123"), "equals", constant(321));
		assertThat(e.toString(), is("\"123\".equals(321)"));
	}

	@Test
	public void parseCallExpression_AbcStringCallEquals123_InstanceConstantIsAbs()
			throws Exception {
		Call e = Call.parse("\"abc\".equals(123)");
		Constant instance = (Constant) e.getInstance();
		assertThat(instance.getValue(), is(equalTo("abc")));
	}

	@Test
	public void parseCallExpression_AbcStringCallEquals123_MethodNameIsEquals()
			throws Exception {
		Call e = Call.parse("\"abc\".equals(123)");
		String methodName = e.getMethodName();
		assertThat(methodName, is("equals"));
	}

	@Test
	public void parseCallExpression_AbcStringCallEquals123_SingleParameterConstantIs123()
			throws Exception {
		Call e = Call.parse("\"abc\".equals(123)");
		Constant instance = (Constant) single(e.getParameters());
		assertThat(instance.getValue(), is(equalTo(123)));
	}

	@Test
	public void createCallMonkeyGetNameExpressionForInstnace_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call(instance(), Monkey.class.getMethod("getName"));
		assertThat(e.toString(), is("obj.getName()"));
	}

	@Test
	public void createCallGetNameExpressionForMonkeyContext_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = equal(call(context(), "getName"), "name");
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
		Expression e = call(constant(monkey), "getRelativeByIndex", constant(0));
		assertThat(e.evaluate(), is(equalTo(expectedRelative)));
	}
}
