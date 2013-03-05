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

	@Test
	public void createLeftShiftExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 << (byte) 1)).getClass();
		Expression e = leftShift((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 << (short) 1)).getClass();
		Expression e = leftShift((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 << (byte) 1)).getClass();
		Expression e = leftShift((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 << (int) 1)).getClass();
		Expression e = leftShift((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 << (byte) 1)).getClass();
		Expression e = leftShift((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 << (long) 1)).getClass();
		Expression e = leftShift((byte) 1, (long) 1);
		assertThat(
				format("Result is {0}, actual {1}", expectedClass.getName(),
						e.getResultClass()),
				e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 << (byte) 1)).getClass();
		Expression e = leftShift((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 << (short) 1)).getClass();
		Expression e = leftShift((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 << (int) 1)).getClass();
		Expression e = leftShift((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 << (short) 1)).getClass();
		Expression e = leftShift((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 << (long) 1)).getClass();
		Expression e = leftShift((short) 1, (long) 1);
		assertThat(
				format("Result is {0}, actual {1}", expectedClass.getName(),
						e.getResultClass()),
				e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 << (short) 1)).getClass();
		Expression e = leftShift((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 << (int) 1)).getClass();
		Expression e = leftShift((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 << (long) 1)).getClass();
		Expression e = leftShift((int) 1, (long) 1);
		assertThat(
				format("Result is {0}, actual {1}", expectedClass.getName(),
						e.getResultClass()),
				e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 << (int) 1)).getClass();
		Expression e = leftShift((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 << (long) 1)).getClass();
		Expression e = leftShift((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLeftShiftExpression_callingToString_getsJavaLikeExpression() {
		Expression e = leftShift(1, 2);
		assertThat(e.toString(), is("1 << 2"));
	}
}
