package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.devide;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class DivideExpressionTests {

	@Test
	public void createDevideExpression_ByteAndByteOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (byte) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDevideExpression_IntAndLongOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (int) 1;
		Object right = (long) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDevideExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDevideExpression_IntAndFloatOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (int) 1;
		Object right = (float) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDevideExpression_FloatAndLongOperands_ReturnClassIsFloat() {
		Class<?> expectedClass = Float.class;
		Object left = (float) 1;
		Object right = (long) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDevideExpression_FloatAndDoubleOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (float) 1;
		Object right = (double) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createDevideExpression_DoubleAndFloatOperands_ReturnClassIsDouble() {
		Class<?> expectedClass = Double.class;
		Object left = (double) 1;
		Object right = (float) 1;
		Expression e = devide(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
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
