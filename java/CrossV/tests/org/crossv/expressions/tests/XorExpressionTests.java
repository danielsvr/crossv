package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class XorExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createXorExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseXor(1, constant(false));
	}

	@Test(expected = IllegalOperandException.class)
	public void createXorExpression_ObjectAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseXor(constant("1"), false);
	}

	@Test(expected = IllegalOperandException.class)
	public void createXorExpression_ObjectAndIntegerOperands_ThrowsIllegalOperandException() {
		bitwiseXor(constant("1"), 1);
	}

	@Test
	public void createXorExpression_BooleanAndBooleanOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)(false & true)).getClass();
		Expression e = bitwiseXor(false, true);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_ByteAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((byte) 1 & (byte) 1)).getClass();
		Expression e = bitwiseXor((byte) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_ByteAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((byte) 1 & (short) 1)).getClass();
		Expression e = bitwiseXor((byte) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_ShortAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (byte) 1)).getClass();
		Expression e = bitwiseXor((short) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createXorExpression_ByteAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((byte) 1 & (long) 1)).getClass();
		Expression e = bitwiseXor((byte) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_LongAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (byte) 1)).getClass();
		Expression e = bitwiseXor((long) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_ShortAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (short) 1)).getClass();
		Expression e = bitwiseXor((short) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_ShortAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (int) 1)).getClass();
		Expression e = bitwiseXor((short) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_IntAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((int) 1 & (short) 1)).getClass();
		Expression e = bitwiseXor((int) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createXorExpression_ShortAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (long) 1)).getClass();
		Expression e = bitwiseXor((byte) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_LongAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (short) 1)).getClass();
		Expression e = bitwiseXor((long) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_IntAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((int) 1 & (int) 1)).getClass();
		Expression e = bitwiseXor((int) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_IntAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((int) 1 & (long) 1)).getClass();
		Expression e = bitwiseXor((int) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_LongAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (int) 1)).getClass();
		Expression e = bitwiseXor((long) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createXorExpression_LongAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (long) 1)).getClass();
		Expression e = bitwiseXor((long) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createXorExpression_callingToString_getsJavaLikeExpression() {
		Expression e = bitwiseXor(1, 2);
		assertThat(e.toString(), is("1 ^ 2"));
	}
}
