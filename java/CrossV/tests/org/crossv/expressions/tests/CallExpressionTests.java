package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.call;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.equal;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class CallExpressionTests {
	
	@Test
	public void createCallHashCodeExpressionForString_callingToString_getsJavaLikeExpression() throws Exception {
		Expression e = call("123", "hashCode");
		assertThat(e.toString(), is("\"123\".hashCode()"));
	}

	@Test
	public void createCallEqualsExpressionForString_callingToString_getsJavaLikeExpression() throws Exception {
		Expression e = call("123", "equals", 321);
		assertThat(e.toString(), is("\"123\".equals(321)"));
	}

	@Test
	public void createCallGetNameExpressionForMonkeyContext_callingToString_getsJavaLikeExpression() throws Exception  {
		Expression e = equal(call(context(Monkey.class), "getName"), "name");
		assertThat(e.toString(), is("(context.getName() == \"name\")"));
	}
}
