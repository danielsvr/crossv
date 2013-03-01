package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.modulo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class ModuloExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createModuloExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		modulo(1, false);
	}

	@Test
	public void createModuloExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 % (byte) 1)).getClass();
		Expression e = modulo((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 % (short) 1)).getClass();
		Expression e = modulo((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 % (byte) 1)).getClass();
		Expression e = modulo((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 % (int) 1)).getClass();
		Expression e = modulo((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 % (byte) 1)).getClass();
		Expression e = modulo((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 % (long) 1)).getClass();
		Expression e = modulo((byte) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 % (byte) 1)).getClass();
		Expression e = modulo((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ByteAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 % (float) 1)).getClass();
		Expression e = modulo((byte) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_FloatAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 % (byte) 1)).getClass();
		Expression e = modulo((float) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ByteAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 % (double) 1)).getClass();
		Expression e = modulo((byte) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_DoubleAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 % (byte) 1)).getClass();
		Expression e = modulo((double) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 % (short) 1)).getClass();
		Expression e = modulo((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 % (int) 1)).getClass();
		Expression e = modulo((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 % (short) 1)).getClass();
		Expression e = modulo((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 % (long) 1)).getClass();
		Expression e = modulo((short) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 % (short) 1)).getClass();
		Expression e = modulo((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ShortAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 % (float) 1)).getClass();
		Expression e = modulo((short) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_FloatAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 % (short) 1)).getClass();
		Expression e = modulo((float) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_ShortAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 % (double) 1)).getClass();
		Expression e = modulo((short) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_DoubleAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 % (short) 1)).getClass();
		Expression e = modulo((double) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 % (int) 1)).getClass();
		Expression e = modulo((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 % (long) 1)).getClass();
		Expression e = modulo((int) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 % (int) 1)).getClass();
		Expression e = modulo((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 % (float) 1)).getClass();
		Expression e = modulo((int) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_FloatAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 % (int) 1)).getClass();
		Expression e = modulo((float) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 % (double) 1)).getClass();
		Expression e = modulo((int) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_DoubleAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 % (int) 1)).getClass();
		Expression e = modulo((double) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 % (long) 1)).getClass();
		Expression e = modulo((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_LongAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 % (float) 1)).getClass();
		Expression e = modulo((long) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_FloatAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 % (long) 1)).getClass();
		Expression e = modulo((float) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_LongAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 % (double) 1)).getClass();
		Expression e = modulo((long) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_DoubleAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 % (long) 1)).getClass();
		Expression e = modulo((double) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_FloatAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 % (float) 1)).getClass();
		Expression e = modulo((float) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_FloatAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 % (double) 1)).getClass();
		Expression e = modulo((float) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_DoubleAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 % (float) 1)).getClass();
		Expression e = modulo((double) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_DoubleAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 % (double) 1))
				.getClass();
		Expression e = modulo((double) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_callingToString_getsJavaLikeExpression() {
		Expression e = modulo(1, 2);
		assertThat(e.toString(), is("1 % 2"));
	}
}
