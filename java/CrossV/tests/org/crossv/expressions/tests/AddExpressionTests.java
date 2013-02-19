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
	public void createAddExpression_IntAndLongOperands_ReturnedClassIsLong() {
		Expression e = add((int)1, (long)1);
		assertThat("Result is Long.class", e.getResultClass().equals(Long.class), is(true));
	}

	@Test
	public void createAddExpression_ShortAndDoubleOperands_ReturnedClassIsLong() {
		Expression e = add((short)1, (double)1);
		assertThat("Result is Double.class", e.getResultClass().equals(Double.class), is(true));
	}

	@Test
	public void createAddExpression_callingToString_getsJavaLikeExpression() {
		Expression e = add(1, 2);
		assertThat(e.toString(), is("1 + 2"));
	}
}
