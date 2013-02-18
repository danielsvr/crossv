package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.crossv.expressions.Expression;
import org.junit.Test;

public class CastExpressionTests {

	@Test
	public void createCastOfValueExpression_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = cast(Object.class, 123);
		assertThat(e.toString(), is("(java.lang.Object)123"));
	}

	@Test
	public void createCastOfContextValueExpression_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = cast(String.class, context());
		assertThat(e.toString(), is("(java.lang.String)context"));
	}
}
