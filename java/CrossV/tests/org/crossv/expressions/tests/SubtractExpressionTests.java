package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.subtract;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class SubtractExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createSubtractExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		subtract(1, false);
	}

	@Test
	public void createSubtractExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 - (byte) 1)).getClass();
		Expression e = subtract((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 - (short) 1)).getClass();
		Expression e = subtract((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 - (byte) 1)).getClass();
		Expression e = subtract((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 - (int) 1)).getClass();
		Expression e = subtract((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 - (byte) 1)).getClass();
		Expression e = subtract((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 - (long) 1)).getClass();
		Expression e = subtract((byte) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 - (byte) 1)).getClass();
		Expression e = subtract((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ByteAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 - (float) 1)).getClass();
		Expression e = subtract((byte) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_FloatAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 - (byte) 1)).getClass();
		Expression e = subtract((float) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ByteAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 - (double) 1)).getClass();
		Expression e = subtract((byte) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_DoubleAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 - (byte) 1)).getClass();
		Expression e = subtract((double) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 - (short) 1)).getClass();
		Expression e = subtract((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 - (int) 1)).getClass();
		Expression e = subtract((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 - (short) 1)).getClass();
		Expression e = subtract((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 - (long) 1)).getClass();
		Expression e = subtract((short) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 - (short) 1)).getClass();
		Expression e = subtract((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ShortAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 - (float) 1)).getClass();
		Expression e = subtract((short) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_FloatAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 - (short) 1)).getClass();
		Expression e = subtract((float) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_ShortAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 - (double) 1)).getClass();
		Expression e = subtract((short) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_DoubleAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 - (short) 1)).getClass();
		Expression e = subtract((double) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 - (int) 1)).getClass();
		Expression e = subtract((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 - (long) 1)).getClass();
		Expression e = subtract((int) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 - (int) 1)).getClass();
		Expression e = subtract((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_IntAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 - (float) 1)).getClass();
		Expression e = subtract((int) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_FloatAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 - (int) 1)).getClass();
		Expression e = subtract((float) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_IntAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 - (double) 1)).getClass();
		Expression e = subtract((int) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_DoubleAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 - (int) 1)).getClass();
		Expression e = subtract((double) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 - (long) 1)).getClass();
		Expression e = subtract((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_LongAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 - (float) 1)).getClass();
		Expression e = subtract((long) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_FloatAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 - (long) 1)).getClass();
		Expression e = subtract((float) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_LongAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 - (double) 1)).getClass();
		Expression e = subtract((long) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_DoubleAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 - (long) 1)).getClass();
		Expression e = subtract((double) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_FloatAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 - (float) 1)).getClass();
		Expression e = subtract((float) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_FloatAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 - (double) 1)).getClass();
		Expression e = subtract((float) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_DoubleAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 - (float) 1)).getClass();
		Expression e = subtract((double) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_DoubleAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 - (double) 1))
				.getClass();
		Expression e = subtract((double) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createSubtractExpression_callingToString_getsJavaLikeExpression() {
		Expression e = subtract(1, 2);
		assertThat(e.toString(), is("1 - 2"));
	}
}
