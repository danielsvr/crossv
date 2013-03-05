package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.crossv.expressions.Expression;
import org.junit.Test;

public class CastExpressionTests {

	@Test
	public void createCastOfValueExpression_ReturnClassIsSameAsProvided()
			throws Exception {
		Expression e = cast(Byte.class, 123);
		assertThat("Return class is Byte", e.getResultClass()
				.equals(Byte.class), is(true));
	}

	@Test
	public void createCastOfContextValueExpression_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = cast(String.class, context());
		assertThat(e.toString(), is("(java.lang.String)context"));
	}
}
