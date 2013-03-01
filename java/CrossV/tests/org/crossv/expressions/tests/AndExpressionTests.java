package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AndExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseAnd(1, constant(false));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_ObjectAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseAnd(constant("1"), false);
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_ObjectAndIntegerOperands_ThrowsIllegalOperandException() {
		bitwiseAnd(constant("1"), 1);
	}

	@Test
	public void createAndExpression_BooleanAndBooleanOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)(false & true)).getClass();
		Expression e = bitwiseAnd(false, true);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_ByteAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((byte) 1 & (byte) 1)).getClass();
		Expression e = bitwiseAnd((byte) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_ByteAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((byte) 1 & (short) 1)).getClass();
		Expression e = bitwiseAnd((byte) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_ShortAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (byte) 1)).getClass();
		Expression e = bitwiseAnd((short) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createAndExpression_ByteAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((byte) 1 & (long) 1)).getClass();
		Expression e = bitwiseAnd((byte) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_LongAndByteOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (byte) 1)).getClass();
		Expression e = bitwiseAnd((long) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_ShortAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (short) 1)).getClass();
		Expression e = bitwiseAnd((short) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_ShortAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (int) 1)).getClass();
		Expression e = bitwiseAnd((short) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_IntAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((int) 1 & (short) 1)).getClass();
		Expression e = bitwiseAnd((int) 1, (short) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createAndExpression_ShortAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((short) 1 & (long) 1)).getClass();
		Expression e = bitwiseAnd((byte) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_LongAndShortOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (short) 1)).getClass();
		Expression e = bitwiseAnd((long) 1, (byte) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_IntAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((int) 1 & (int) 1)).getClass();
		Expression e = bitwiseAnd((int) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_IntAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((int) 1 & (long) 1)).getClass();
		Expression e = bitwiseAnd((int) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_LongAndIntOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (int) 1)).getClass();
		Expression e = bitwiseAnd((long) 1, (int) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createAndExpression_LongAndLongOperands_ReturnsSameClassLikeJava() {
		Class<?> expectedClass = ((Object)((long) 1 & (long) 1)).getClass();
		Expression e = bitwiseAnd((long) 1, (long) 1);
		assertThat(format("Result class is {0}", expectedClass.getName()), e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		Expression e = bitwiseAnd(1, 2);
		assertThat(e.toString(), is("1 & 2"));
	}
}
