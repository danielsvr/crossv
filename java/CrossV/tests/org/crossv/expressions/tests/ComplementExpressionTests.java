package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.complemented;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class ComplementExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createComplementedStringExpression_ThrowsIllegalOperandException() {
		complemented("string");
	}

	@Test(expected = IllegalOperandException.class)
	public void createComplementedDoubleExpression_ThrowsIllegalOperandException() {
		complemented((double) 1);
	}

	@Test
	public void createNegatedIntExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = complemented((int) -1);
		assertThat(e.toString(), is("~(-1)"));
	}

	@Test
	public void createNegatedDoubleContextValue_callingToString_getsJavaLikeExpression() {
		Expression e = complemented(context(Long.class));
		assertThat(e.toString(), is("~context"));
	}

	@Test
	public void evaluateNegatedExpression_NegativeValue_ReturnsPositiveValue()
			throws Exception {
		int value = -1;
		Expression e = complemented(value);
		assertThat(e.evaluate(), is(equalTo(~(-1))));
	}

	@Test
	public void evaluateNegatedExpression_PositoveLongValue_ReturnsNegativeLongValue()
			throws Exception {
		long value = 1;
		Expression e = complemented(value);
		assertThat(e.evaluate(), is(equalTo(~1L)));
	}
}
