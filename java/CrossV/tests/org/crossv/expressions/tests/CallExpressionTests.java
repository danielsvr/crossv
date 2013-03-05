package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.call;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.equal;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class CallExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createCallGetRelativesAsListMethodOfMonkeyClassExpressionForString_ThrowsIllegalOperandException()
			throws Exception {
		call(constant("123"), Monkey.class.getMethod("getRelativesAsList"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createCallSetNameExpressionForMonkey_ThrowsIllegalOperandException()
			throws Exception {
		call(constant(new Monkey()),
				Monkey.class.getMethod("setName", String.class));
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
}
