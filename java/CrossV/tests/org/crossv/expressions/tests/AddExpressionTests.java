package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.add;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AddExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAddExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		add(1, false);
	}

	@Test
	public void createAddExpression_StringAndObjectOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ("1" + new Object())).getClass();
		Expression e = add("1", new Object());
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ObjectAndStringOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) (new Object() + "1")).getClass();
		Expression e = add(new Object(), "1");
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ByteAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 + (byte) 1)).getClass();
		Expression e = add((byte) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ByteAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 + (short) 1)).getClass();
		Expression e = add((byte) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ShortAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 + (byte) 1)).getClass();
		Expression e = add((short) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ByteAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 + (int) 1)).getClass();
		Expression e = add((byte) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_IntAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 + (byte) 1)).getClass();
		Expression e = add((int) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ByteAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 + (long) 1)).getClass();
		Expression e = add((byte) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_LongAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 + (byte) 1)).getClass();
		Expression e = add((long) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ByteAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 + (float) 1)).getClass();
		Expression e = add((byte) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_FloatAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 + (byte) 1)).getClass();
		Expression e = add((float) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ByteAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 + (double) 1)).getClass();
		Expression e = add((byte) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndByteOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 + (byte) 1)).getClass();
		Expression e = add((double) 1, (byte) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ShortAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 + (short) 1)).getClass();
		Expression e = add((short) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ShortAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 + (int) 1)).getClass();
		Expression e = add((short) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_IntAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 + (short) 1)).getClass();
		Expression e = add((int) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ShortAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 + (long) 1)).getClass();
		Expression e = add((short) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_LongAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 + (short) 1)).getClass();
		Expression e = add((long) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ShortAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 + (float) 1)).getClass();
		Expression e = add((short) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_FloatAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 + (short) 1)).getClass();
		Expression e = add((float) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_ShortAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((short) 1 + (double) 1)).getClass();
		Expression e = add((short) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndShortOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 + (short) 1)).getClass();
		Expression e = add((double) 1, (short) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 + (int) 1)).getClass();
		Expression e = add((int) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_IntAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 + (long) 1)).getClass();
		Expression e = add((int) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_LongAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 + (int) 1)).getClass();
		Expression e = add((long) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_IntAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 + (float) 1)).getClass();
		Expression e = add((int) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_FloatAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 + (int) 1)).getClass();
		Expression e = add((float) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_IntAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((int) 1 + (double) 1)).getClass();
		Expression e = add((int) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 + (int) 1)).getClass();
		Expression e = add((double) 1, (int) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_LongAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 + (long) 1)).getClass();
		Expression e = add((long) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_LongAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 + (float) 1)).getClass();
		Expression e = add((long) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_FloatAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 + (long) 1)).getClass();
		Expression e = add((float) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_LongAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((long) 1 + (double) 1)).getClass();
		Expression e = add((long) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndLongOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 + (long) 1)).getClass();
		Expression e = add((double) 1, (long) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_FloatAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 + (float) 1)).getClass();
		Expression e = add((float) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_FloatAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((float) 1 + (double) 1)).getClass();
		Expression e = add((float) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndFloatOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 + (float) 1)).getClass();
		Expression e = add((double) 1, (float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndDoubleOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) ((double) 1 + (double) 1))
				.getClass();
		Expression e = add((double) 1, (double) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAddExpression_callingToString_getsJavaLikeExpression() {
		Expression e = add(1, 2);
		assertThat(e.toString(), is("1 + 2"));
	}
}
