package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.modulo;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class ModuloExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createModuloExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		modulo(1, false);
	}

	@Test
	public void createModuloExpression_IntAndShortOperands_ReturnClassIsInt() {
		Class<?> expectedClass = Integer.class;
		Object left = (int) 1;
		Object right = (short) 1;
		Expression e = modulo(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createModuloExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = modulo(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createModuloExpression_FloatAndLongOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (float) 1;
		Object right = (long) 1;
		Expression e = modulo(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createModuloExpression_IntAndDoubleOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (int) 1;
		Object right = (double) 1;
		Expression e = modulo(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createModuloExpression_callingToString_getsJavaLikeExpression() {
		Expression e = modulo(1, 2);
		assertThat(e.toString(), is("1 % 2"));
	}

	@Test
	public void evaluateModuloExpression_OneAsIntAndTwoAsByte_ReturnsOneAsInt()
			throws Exception {
		Expression e = modulo(1, (byte) 2);
		assertThat(e.evaluate(), is(equalTo(1)));
	}

	@Test
	public void evaluateModuloExpression_TwoAsIntAndOneAsByte_ReturnsZeroAsInt()
			throws Exception {
		Expression e = modulo(2, (byte) 1);
		assertThat(e.evaluate(), is(equalTo(0)));
	}

	@Test
	public void evaluateModuloExpression_TwoAsIntAndOneAsLong_ReturnsZeroAsLong()
			throws Exception {
		Expression e = modulo(2, 1L);
		assertThat(e.evaluate(), is(equalTo(0L)));
	}

	@Test
	public void evaluateModuloExpression_TwoAsIntAndOneAsFloat_ReturnsZeroFloat()
			throws Exception {
		Expression e = modulo(2, 1f);
		assertThat(e.evaluate(), is(equalTo(0f)));
	}

	@Test
	public void evaluateModuloExpression_1AsIntAndTwoAsFloat_ReturnsOneAsFloat()
			throws Exception {
		Expression e = modulo(1, 2f);
		assertThat(e.evaluate(), is(equalTo(1f)));
	}

	@Test
	public void evaluateModuloExpression_TwoAsIntAndOneAsDouble_ReturnsZeroAsDouble()
			throws Exception {
		Expression e = modulo(2, 1d);
		assertThat(e.evaluate(), is(equalTo(0d)));
	}
}
