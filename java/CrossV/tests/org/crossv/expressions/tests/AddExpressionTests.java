package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.add;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AddExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAddExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		add(1, false);
	}

	@Test
	public void createAddExpression_StringAndIntOperands_ReturnedClassIsString() {
		Expression e = add("1", (int)1);
		assertThat("Result is String.class", e.getResultClass().equals(String.class), is(true));
	}

	@Test
	public void createAddExpression_IntAndStringOperands_ReturnedClassIsString() {
		Expression e = add((int)1, "1");
		assertThat("Result is String.class", e.getResultClass().equals(String.class), is(true));
	}

	@Test
	public void createAddExpression_ByteAndByteOperands_ReturnedClassIsByte() {
		Expression e = add((byte)1, (byte)1);
		assertThat("Result is Byte.class", e.getResultClass().equals(Byte.class), is(true));
	}
	
	@Test
	public void createAddExpression_ByteAndShortOperands_ReturnedClassIsShort() {
		Expression e = add((byte)1, (short)1);
		assertThat("Result is Short.class", e.getResultClass().equals(Short.class), is(true));
	}
	
	@Test
	public void createAddExpression_ShortAndByteOperands_ReturnedClassIsShort() {
		Expression e = add((short)1, (byte)1);
		assertThat("Result is Short.class", e.getResultClass().equals(Short.class), is(true));
	}	
	
	@Test
	public void createAddExpression_ByteAndDoubleOperands_ReturnedClassIsDouble() {
		Expression e = add((byte)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndByteOperands_ReturnedClassIsDouble() {
		Expression e = add((double)1, (byte)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}
	
	@Test
	public void createAddExpression_ShortAndShortOperands_ReturnedClassIsShort() {
		Expression e = add((short)1, (short)1);
		assertThat("Result is Short.class", e.getResultClass().equals(Short.class), is(true));
	}
	
	@Test
	public void createAddExpression_ShortAndIntOperands_ReturnedClassIsInt() {
		Expression e = add((short)1, (int)1);
		assertThat("Result is Integer.class", e.getResultClass().equals(Integer.class), is(true));
	}
	
	@Test
	public void createAddExpression_IntAndShortOperands_ReturnedClassIsInt() {
		Expression e = add((int)1, (short)1);
		assertThat("Result is Integer.class", e.getResultClass().equals(Integer.class), is(true));
	}	

	@Test
	public void createAddExpression_ShortAndDoubleOperands_ReturnedClassIsDouble() {
		Expression e = add((short)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndShortOperands_ReturnedClassIsDouble() {
		Expression e = add((double)1, (short)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}
	
	@Test
	public void createAddExpression_IntAndIntOperands_ReturnedClassIsInt() {
		Expression e = add((int)1, (int)1);
		assertThat("Result is Integer.class", e.getResultClass().equals(Integer.class), is(true));
	}
	
	@Test
	public void createAddExpression_IntAndLongOperands_ReturnedClassIsLong() {
		Expression e = add((int)1, (long)1);
		assertThat("Result is Long.class", e.getResultClass().equals(Long.class), is(true));
	}
	
	@Test
	public void createAddExpression_LongAndIntOperands_ReturnedClassIsLong() {
		Expression e = add((long)1, (int)1);
		assertThat("Result is Long.class", e.getResultClass().equals(Long.class), is(true));
	}	

	@Test
	public void createAddExpression_IntAndDoubleOperands_ReturnedClassIsDouble() {
		Expression e = add((int)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndIntOperands_ReturnedClassIsDouble() {
		Expression e = add((double)1, (int)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}
	
	@Test
	public void createAddExpression_LongAndLongOperands_ReturnedClassIsLong() {
		Expression e = add((long)1, (long)1);
		assertThat("Result is Long.class", e.getResultClass().equals(Long.class), is(true));
	}
	
	@Test
	public void createAddExpression_LongAndFloatOperands_ReturnedClassIsFloat() {
		Expression e = add((long)1, (float)1);
		assertThat("Result is Float.class", e.getResultClass().equals(Float.class), is(true));
	}
	
	@Test
	public void createAddExpression_FloatAndLongOperands_ReturnedClassIsFloat() {
		Expression e = add((float)1, (long)1);
		assertThat("Result is Float.class", e.getResultClass().equals(Float.class), is(true));
	}	

	@Test
	public void createAddExpression_LongAndDoubleOperands_ReturnedClassIsDouble() {
		Expression e = add((long)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndLongOperands_ReturnedClassIsDouble() {
		Expression e = add((double)1, (long)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_FloatAndFloatOperands_ReturnedClassIsDouble() {
		Expression e = add((float)1, (float)1);
		assertThat("Result is Float.class", e.getResultClass().equals(Float.class), is(true));
	}

	@Test
	public void createAddExpression_FloatAndDoubleOperands_ReturnedClassIsDouble() {
		Expression e = add((float)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndFloatOperands_ReturnedClassIsDouble() {
		Expression e = add((double)1, (float)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_DoubleAndDoubleOperands_ReturnedClassIsDouble() {
		Expression e = add((double)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}
	
	@Test
	public void createAddExpression_callingToString_getsJavaLikeExpression() {
		Expression e = add(1, 2);
		assertThat(e.toString(), is("1 + 2"));
	}
}
