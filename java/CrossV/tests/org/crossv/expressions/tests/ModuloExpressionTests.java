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
	public void createModuloExpression_IntAndShortOperands_ReturnClassIsInt() {
		Class<?> expectedClass = Integer.class;
		Object left = (int) 1;
		Object right = (short) 1;
		Expression e = modulo(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createModuloExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = modulo(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}
	
	@Test
	public void createModuloExpression_FloatAndLongOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (float) 1;
		Object right = (long) 1;
		Expression e = modulo(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_IntAndDoubleOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (int) 1;
		Object right = (double) 1;
		Expression e = modulo(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createModuloExpression_callingToString_getsJavaLikeExpression() {
		Expression e = modulo(1, 2);
		assertThat(e.toString(), is("1 % 2"));
	}
}
