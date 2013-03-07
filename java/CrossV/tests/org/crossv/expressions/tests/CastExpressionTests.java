package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.cast;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.junit.Test;

public class CastExpressionTests {

	@Test
	public void createCastOfValueExpression_ReturnClassIsSameAsProvided()
			throws Exception {
		Expression e = cast(Byte.class, 123);
		assertThat(e.getResultClass(), is(assignableTo(Byte.class)));
	}

	@Test
	public void createCastOfContextValueExpression_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = cast(String.class, context());
		assertThat(e.toString(), is("(java.lang.String)context"));
	}

	@Test
	public void evaluateCastExpression_IntToDouble_ReturnsDoubleValue()
			throws Exception {
		int value = 0;
		Expression e = cast(Double.class, constant(value));
		assertThat(e.evaluate(), is(equalTo(0d)));
	}
}
