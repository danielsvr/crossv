package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.negate;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class NegateExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createNegatedStringExpression_ThrowsIllegalOperandException() {
		negate(constant("string"));
	}

	@Test
	public void createNegatedExpression_NumberValue_PreservesTheReturnClass() {
		Class<?> expectedClass = Float.class;
		Expression e = negate((float) 1);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createNegatedFloatExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((float) 1);
		assertThat(e.toString(), is("-1.0"));
	}

	@Test
	public void createNegatedIntExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((int) -1);
		assertThat(e.toString(), is("-(-1)"));
	}

	@Test
	public void createNegatedDoubleContextValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate(context(Double.class));
		assertThat(e.toString(), is("-context"));
	}

	@Test
	public void evaluateNegatedExpression_PositiveValue_ReturnsNegativeValue()
			throws Exception {
		float value = 1;
		Expression e = negate(value);
		assertThat(e.evaluate(), is(equalTo(-1f)));
	}

	@Test
	public void evaluateNegatedExpression_NegativeValue_ReturnsPositiveValue()
			throws Exception {
		int value = -1;
		Expression e = negate(value);
		assertThat(e.evaluate(), is(equalTo(1)));
	}

	@Test
	public void evaluateNegatedExpression_PositoveLongValue_ReturnsNegativeLongValue()
			throws Exception {
		long value = 1;
		Expression e = negate(value);
		assertThat(e.evaluate(), is(equalTo(-1L)));
	}

	@Test
	public void evaluateNegatedExpression_NegativeDoubleValue_ReturnsPositiveDoubleValue()
			throws Exception {
		double value = -1;
		Expression e = negate(value);
		assertThat(e.evaluate(), is(equalTo(1d)));
	}
}
