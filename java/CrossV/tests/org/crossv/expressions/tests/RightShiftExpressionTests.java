package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.rightShift;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class RightShiftExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createRightShiftExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		rightShift(1, false);
	}

	@Test(expected = IllegalOperandException.class)
	public void createRightShiftExpression_BooleanAndIntOperands_ThrowsIllegalOperandException() {
		rightShift(true, 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createRightShiftExpression_IntAndDoubleOperands_ThrowsIllegalOperandException() {
		rightShift(1, (double) 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createRightShiftExpression_FloatAndIntOperands_ThrowsIllegalOperandException() {
		rightShift((float) 1, 1);
	}

	@Test
	public void createRightShiftExpression_ByteAndIntOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = rightShift(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createRightShiftExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = rightShift(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createRightShiftExpression_IntAndLongOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (int) 1;
		Object right = (long) 1;
		Expression e = rightShift(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createRightShiftExpression_callingToString_getsJavaLikeExpression() {
		Expression e = rightShift(1, 2);
		assertThat(e.toString(), is("1 >> 2"));
	}

	@Test
	public void evaluateRightShiftExpression_OneByteAndOneIntValue_ReturnsOneAsInt()
			throws Exception {
		Object left = (byte) 2;
		Object right = (int) 1;
		Expression e = rightShift(left, right);
		assertThat(e.evaluate(), is(equalTo(1)));
	}

	@Test
	public void evaluateRightShiftExpression_OneLongAndOneIntValue_ReturnsOneAsLong()
			throws Exception {
		Object left = (long) 2;
		Object right = (int) 1;
		Expression e = rightShift(left, right);
		assertThat(e.evaluate(), is(equalTo(1L)));
	}

	@Test
	public void evaluateRightShiftExpression_OneIntAndOneLongValue_ReturnsOneAsInt()
			throws Exception {
		Object left = (int) 2;
		Object right = (long) 1;
		Expression e = rightShift(left, right);
		assertThat(e.evaluate(), is(equalTo(1)));
	}
}
