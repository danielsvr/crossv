package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.call;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.equal;
import static org.crossv.tests.helpers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class CallExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createCall_WithNullInstanceAndSomeMethod_ThrowsIllegalArgumentException()
			throws Exception {
		call(null, Monkey.class.getMethod("getName"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createCall_WithSomeInstanceAndNullMethod_ThrowsIllegalArgumentException()
			throws Exception {
		call(constant("instance"), (Method) null);
	}

	@Test(expected = IllegalOperandException.class)
	public void createCallGetRelativesAsListMethodOfMonkeyClassExpressionForString_ThrowsIllegalOperandException()
			throws Exception {
		call(constant("123"), Monkey.class.getMethod("getRelativesAsList"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createCallSetNameExpressionForMonkey_ThrowsIllegalOperandException()
			throws Exception {
		Monkey monkney = TestObjectFactory.createMonkey();

		call(constant(monkney), Monkey.class.getMethod("setName", String.class));
	}

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
