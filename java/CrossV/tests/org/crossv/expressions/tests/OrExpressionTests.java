package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class OrExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseOr(1, constant(false));
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_ObjectAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseOr(constant("1"), false);
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_ObjectAndIntegerOperands_ThrowsIllegalOperandException() {
		bitwiseOr(constant("1"), 1);
	}

	@Test
	public void createOrExpression_BooleanAndBooleanOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) (false | true)).getClass();
		Expression e = bitwiseOr(false, true);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ByteAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 | (byte) 1)).getClass();
		Expression e = bitwiseOr((byte) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ByteAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 | (short) 1)).getClass();
		Expression e = bitwiseOr((byte) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ShortAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((short) 1 | (byte) 1)).getClass();
		Expression e = bitwiseOr((short) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ByteAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((byte) 1 | (long) 1)).getClass();
		Expression e = bitwiseOr((byte) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_LongAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((long) 1 | (byte) 1)).getClass();
		Expression e = bitwiseOr((long) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ShortAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((short) 1 | (short) 1)).getClass();
		Expression e = bitwiseOr((short) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ShortAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((short) 1 | (int) 1)).getClass();
		Expression e = bitwiseOr((short) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_IntAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((int) 1 | (short) 1)).getClass();
		Expression e = bitwiseOr((int) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_ShortAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((short) 1 | (long) 1)).getClass();
		Expression e = bitwiseOr((byte) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_LongAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((long) 1 | (short) 1)).getClass();
		Expression e = bitwiseOr((long) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_IntAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((int) 1 | (int) 1)).getClass();
		Expression e = bitwiseOr((int) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_IntAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((int) 1 | (long) 1)).getClass();
		Expression e = bitwiseOr((int) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_LongAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((long) 1 | (int) 1)).getClass();
		Expression e = bitwiseOr((long) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_LongAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object) ((long) 1 | (long) 1)).getClass();
		Expression e = bitwiseOr((long) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createOrExpression_callingToString_getsJavaLikeExpression() {
		Expression e = bitwiseOr(1, 2);
		assertThat(e.toString(), is("1 | 2"));
	}
}
