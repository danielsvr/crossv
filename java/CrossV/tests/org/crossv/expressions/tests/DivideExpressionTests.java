package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.devide;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class DivideExpressionTests {
	
	@Test
	public void createDevideExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 / (byte) 1)).getClass();
		Expression e = devide((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 / (short) 1)).getClass();
		Expression e = devide((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 / (byte) 1)).getClass();
		Expression e = devide((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 / (int) 1)).getClass();
		Expression e = devide((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 / (byte) 1)).getClass();
		Expression e = devide((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 / (long) 1)).getClass();
		Expression e = devide((byte) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 / (byte) 1)).getClass();
		Expression e = devide((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_ByteAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 / (float) 1)).getClass();
		Expression e = devide((byte) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_FloatAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 / (byte) 1)).getClass();
		Expression e = devide((float) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_ByteAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 / (double) 1)).getClass();
		Expression e = devide((byte) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_DoubleAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 / (byte) 1)).getClass();
		Expression e = devide((double) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 / (short) 1)).getClass();
		Expression e = devide((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 / (int) 1)).getClass();
		Expression e = devide((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 / (short) 1)).getClass();
		Expression e = devide((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 / (long) 1)).getClass();
		Expression e = devide((short) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 / (short) 1)).getClass();
		Expression e = devide((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_ShortAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 / (float) 1)).getClass();
		Expression e = devide((short) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_FloatAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 / (short) 1)).getClass();
		Expression e = devide((float) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_ShortAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 / (double) 1)).getClass();
		Expression e = devide((short) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_DoubleAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 / (short) 1)).getClass();
		Expression e = devide((double) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 / (int) 1)).getClass();
		Expression e = devide((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 / (long) 1)).getClass();
		Expression e = devide((int) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 / (int) 1)).getClass();
		Expression e = devide((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_IntAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 / (float) 1)).getClass();
		Expression e = devide((int) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_FloatAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 / (int) 1)).getClass();
		Expression e = devide((float) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_IntAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 / (double) 1)).getClass();
		Expression e = devide((int) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_DoubleAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 / (int) 1)).getClass();
		Expression e = devide((double) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 / (long) 1)).getClass();
		Expression e = devide((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_LongAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 / (float) 1)).getClass();
		Expression e = devide((long) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_FloatAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 / (long) 1)).getClass();
		Expression e = devide((float) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_LongAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 / (double) 1)).getClass();
		Expression e = devide((long) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_DoubleAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 / (long) 1)).getClass();
		Expression e = devide((double) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_FloatAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 / (float) 1)).getClass();
		Expression e = devide((float) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_FloatAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 / (double) 1)).getClass();
		Expression e = devide((float) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createDevideExpression_DoubleAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 / (float) 1)).getClass();
		Expression e = devide((double) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_DoubleAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 / (double) 1)).getClass();
		Expression e = devide((double) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createDevideExpression_callingToString_getsJavaLikeExpression() {
		Expression e = devide(1, 2);
		assertThat(e.toString(), is("1 / 2"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAddExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		devide(1, false);
	}
}
