package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.multiply;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class MultiplyExpressionTests {
	
	@Test
	public void createMultiplyExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 * (byte) 1)).getClass();
		Expression e = multiply((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 * (short) 1)).getClass();
		Expression e = multiply((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 * (byte) 1)).getClass();
		Expression e = multiply((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 * (int) 1)).getClass();
		Expression e = multiply((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 * (byte) 1)).getClass();
		Expression e = multiply((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 * (long) 1)).getClass();
		Expression e = multiply((byte) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 * (byte) 1)).getClass();
		Expression e = multiply((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_ByteAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 * (float) 1)).getClass();
		Expression e = multiply((byte) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_FloatAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 * (byte) 1)).getClass();
		Expression e = multiply((float) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_ByteAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 * (double) 1)).getClass();
		Expression e = multiply((byte) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_DoubleAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 * (byte) 1)).getClass();
		Expression e = multiply((double) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 * (short) 1)).getClass();
		Expression e = multiply((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 * (int) 1)).getClass();
		Expression e = multiply((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 * (short) 1)).getClass();
		Expression e = multiply((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 * (long) 1)).getClass();
		Expression e = multiply((short) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 * (short) 1)).getClass();
		Expression e = multiply((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_ShortAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 * (float) 1)).getClass();
		Expression e = multiply((short) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_FloatAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 * (short) 1)).getClass();
		Expression e = multiply((float) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_ShortAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 * (double) 1)).getClass();
		Expression e = multiply((short) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_DoubleAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 * (short) 1)).getClass();
		Expression e = multiply((double) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 * (int) 1)).getClass();
		Expression e = multiply((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 * (long) 1)).getClass();
		Expression e = multiply((int) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 * (int) 1)).getClass();
		Expression e = multiply((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_IntAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 * (float) 1)).getClass();
		Expression e = multiply((int) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_FloatAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 * (int) 1)).getClass();
		Expression e = multiply((float) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_IntAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 * (double) 1)).getClass();
		Expression e = multiply((int) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_DoubleAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 * (int) 1)).getClass();
		Expression e = multiply((double) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 * (long) 1)).getClass();
		Expression e = multiply((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_LongAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 * (float) 1)).getClass();
		Expression e = multiply((long) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_FloatAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 * (long) 1)).getClass();
		Expression e = multiply((float) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_LongAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 * (double) 1)).getClass();
		Expression e = multiply((long) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_DoubleAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 * (long) 1)).getClass();
		Expression e = multiply((double) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_FloatAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 * (float) 1)).getClass();
		Expression e = multiply((float) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_FloatAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 * (double) 1)).getClass();
		Expression e = multiply((float) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createMultiplyExpression_DoubleAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 * (float) 1)).getClass();
		Expression e = multiply((double) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createMultiplyExpression_DoubleAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 * (double) 1)).getClass();
		Expression e = multiply((double) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
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
}
