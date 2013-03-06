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
	public void createAndExpression_BooleanAndIntOperands_ThrowsIllegalOperandException() {
		bitwiseAnd(constant(false), 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_DoubleAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseAnd((double) 1, constant(false));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_DoubleAndByteOperands_ThrowsIllegalOperandException() {
		bitwiseAnd((double) 1, (byte) 1);
	}

	@Test
	public void createAndExpression_BooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = false;
		boolean right = true;
		Expression e = bitwiseAnd(left, right);
		assertThat(format("Result class is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_NumericOperandsPromotedToLong_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (byte) 1;
		Object right = (long) 1;
		Expression e = bitwiseAnd(left, right);
		String message = format("Result class should be {0}, actual {1}",
				expectedClass, e.getResultClass());
		assertThat(message, e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_NumericOperandsPromotedToInt_ReturnClassIsInt() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = bitwiseAnd(left, right);
		String message = format("Result class should be {0}, actual {1}",
				expectedClass, e.getResultClass());
		assertThat(message, e.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		Expression e = bitwiseAnd(1, 2);
		assertThat(e.toString(), is("1 & 2"));
	}
}
