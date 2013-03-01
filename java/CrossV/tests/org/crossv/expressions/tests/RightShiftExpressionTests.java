package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.rightShift;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class RightShiftExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createRightShiftExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		rightShift(1, false);
	}

	@Test
	public void createRightShiftExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 >> (byte) 1)).getClass();
		Expression e = rightShift((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 >> (short) 1)).getClass();
		Expression e = rightShift((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 >> (byte) 1)).getClass();
		Expression e = rightShift((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 >> (int) 1)).getClass();
		Expression e = rightShift((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 >> (byte) 1)).getClass();
		Expression e = rightShift((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 >> (long) 1)).getClass();
		Expression e = rightShift((byte) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 >> (byte) 1)).getClass();
		Expression e = rightShift((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 >> (short) 1)).getClass();
		Expression e = rightShift((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 >> (int) 1)).getClass();
		Expression e = rightShift((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 >> (short) 1)).getClass();
		Expression e = rightShift((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 >> (long) 1)).getClass();
		Expression e = rightShift((short) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 >> (short) 1)).getClass();
		Expression e = rightShift((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 >> (int) 1)).getClass();
		Expression e = rightShift((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 >> (long) 1)).getClass();
		Expression e = rightShift((int) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 >> (int) 1)).getClass();
		Expression e = rightShift((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 >> (long) 1)).getClass();
		Expression e = rightShift((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createRightShiftExpression_callingToString_getsJavaLikeExpression() {
		Expression e = rightShift(1, 2);
		assertThat(e.toString(), is("1 >> 2"));
	}
}
