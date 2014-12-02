package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.divide;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class DivideExpressionTests {

	@Test
	public void createDivideExpression_ByteAndByteOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (byte) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_IntAndLongOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (int) 1;
		Object right = (long) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_IntAndFloatOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (int) 1;
		Object right = (float) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_FloatAndLongOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (float) 1;
		Object right = (long) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_FloatAndDoubleOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (float) 1;
		Object right = (double) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_DoubleAndFloatOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (double) 1;
		Object right = (float) 1;
		Expression e = divide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDivideExpression_callingToString_getsJavaLikeExpression() {
		Expression e = divide(1, 2);
		assertThat(e.toString(), is("1 / 2"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createDivideExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		divide(1, false);
	}

	@Test
	public void evaluateDivideExpression_OneAsIntAndTwoAsByte_ReturnsTwo()
			throws Exception {
		Expression e = divide(1, (byte) 2);
		assertThat(e.evaluate(), is(equalTo(0)));
	}

	@Test
	public void evaluateDivideExpression_TwoAsIntAndOneAsByte_ReturnsTwo()
			throws Exception {
		Expression e = divide(2, (byte) 1);
		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateDivideExpression_TwoAsIntAndOneAsLong_ReturnsTwo()
			throws Exception {
		Expression e = divide(2, 1L);
		assertThat(e.evaluate(), is(equalTo(2L)));
	}

	@Test
	public void evaluateDivideExpression_TwoAsIntAndOneAsFloat_ReturnsTwo()
			throws Exception {
		Expression e = divide(2, 1f);
		assertThat(e.evaluate(), is(equalTo(2f)));
	}

	@Test
	public void evaluateDivideExpression_1AsIntAndTwoAsFloat_ReturnsTwo()
			throws Exception {
		Expression e = divide(1, 2f);
		assertThat(e.evaluate(), is(equalTo(0.5f)));
	}

	@Test
	public void evaluateDivideExpression_TwoAsIntAndOneAsDouble_ReturnsTwo()
			throws Exception {
		Expression e = divide(2, 1d);
		assertThat(e.evaluate(), is(equalTo(2d)));
	}
}
