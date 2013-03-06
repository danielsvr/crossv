package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.leftShift;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class LeftShiftExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		leftShift(1, false);
	}

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_BooleanAndIntOperands_ThrowsIllegalOperandException() {
		leftShift(true, 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_IntAndDoubleOperands_ThrowsIllegalOperandException() {
		leftShift(1, (double) 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_FloatAndIntOperands_ThrowsIllegalOperandException() {
		leftShift((float) 1, 1);
	}

	@Test
	public void createLeftShiftExpression_ByteAndIntOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = leftShift(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = leftShift(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_IntAndLongOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (int) 1;
		Object right = (long) 1;
		Expression e = leftShift(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_callingToString_getsJavaLikeExpression() {
		Expression e = leftShift(1, 2);
		assertThat(e.toString(), is("1 << 2"));
	}
}
