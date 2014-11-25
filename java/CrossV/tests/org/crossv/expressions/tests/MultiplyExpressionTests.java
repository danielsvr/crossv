package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.multiply;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class MultiplyExpressionTests {

	@Test
	public void createMultiplyExpression_IntAndShortOperands_ReturnClassIsInt() {
		Class<?> expectedClass = Integer.class;
		Object left = (int) 1;
		Object right = (short) 1;
		Expression e = multiply(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createMultiplyExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = multiply(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createMultiplyExpression_FloatAndLongOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (float) 1;
		Object right = (long) 1;
		Expression e = multiply(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createMultiplyExpression_IntAndDoubleOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (int) 1;
		Object right = (double) 1;
		Expression e = multiply(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createMultiplyExpression_callingToString_getsJavaLikeExpression() {
		Expression e = multiply(1, 2);
		assertThat(e.toString(), is("1 * 2"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAddExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		multiply(1, false);
	}

	@Test
	public void evaluateMultiplyExpression_OneAsIntAndTwoAsByte_ReturnsTwoAsInt()
			throws Exception {
		Expression e = multiply(1, (byte) 2);
		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateMultiplyExpression_TwoAsIntAndOneAsByte_ReturnsTwoAsInt()
			throws Exception {
		Expression e = multiply(2, (byte) 1);
		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateMultiplyExpression_TwoAsIntAndOneAsLong_ReturnsTwoAsLong()
			throws Exception {
		Expression e = multiply(2, 1L);
		assertThat(e.evaluate(), is(equalTo(2L)));
	}

	@Test
	public void evaluateMultiplyExpression_TwoAsIntAndOneAsFloat_ReturnsTwoAsFloat()
			throws Exception {
		Expression e = multiply(2, 1f);
		assertThat(e.evaluate(), is(equalTo(2f)));
	}

	@Test
	public void evaluateMultiplyExpression_1AsIntAndTwoAsFloat_ReturnsTwoAsFloat()
			throws Exception {
		Expression e = multiply(1, 2f);
		assertThat(e.evaluate(), is(equalTo(2f)));
	}

	@Test
	public void evaluateMultiplyExpression_TwoAsIntAndOneAsDouble_ReturnsTwoAsDouble()
			throws Exception {
		Expression e = multiply(2, 1d);
		assertThat(e.evaluate(), is(equalTo(2d)));
	}
}
