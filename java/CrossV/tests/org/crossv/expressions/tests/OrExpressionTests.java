package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.bitwiseOr;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class OrExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseOr(constant(false), 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_ObjectAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseOr((double) 1, constant(false));
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_ObjectAndIntegerOperands_ThrowsIllegalOperandException() {
		bitwiseOr((double) 1, (byte) 1);
	}

	@Test
	public void createOrExpression_BooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = false;
		boolean right = true;
		Expression e = bitwiseOr(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_NumericOperandsPromotedToLong_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (byte) 1;
		Object right = (long) 1;
		Expression e = bitwiseOr(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_NumericOperandsPromotedToInt_ReturnClassIsInt() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = bitwiseOr(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createOrExpression_callingToString_getsJavaLikeExpression() {
		Expression e = bitwiseOr(1, 2);
		assertThat(e.toString(), is("1 | 2"));
	}

	@Test
	public void evaluateOrExpression_FalseAndTrue_ReturnsTrue()
			throws Exception {
		boolean left = false;
		boolean right = true;
		Expression e = bitwiseOr(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateOrExpression_FalseAndFalse_ReturnsFalse()
			throws Exception {
		boolean left = false;
		boolean right = false;
		Expression e = bitwiseOr(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateOrExpression_TrueAndTrue_ReturnsTrue() throws Exception {
		boolean left = true;
		boolean right = true;
		Expression e = bitwiseOr(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateOrExpression_OneOrTwoAsIntegerValues_ReturnsThreeAsInteger()
			throws Exception {
		int left = 1;
		int right = 2;

		Expression e = bitwiseOr(left, right);
		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void evaluateOrExpression_OneAsIntOrTwoAsLong_ReturnsThreeAsLong()
			throws Exception {
		int left = 1;
		long right = 2;

		Expression e = bitwiseOr(left, right);
		assertThat(e.evaluate(), is(equalTo(3L)));
	}
}
