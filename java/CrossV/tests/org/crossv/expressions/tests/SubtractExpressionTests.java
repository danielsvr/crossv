package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.subtract;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class SubtractExpressionTests {
	
	@Test(expected = IllegalOperandException.class)
	public void createSubtractExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		subtract(1, false);
	}

	@Test
	public void createSubtractExpression_NumberOperands_ReturnClassIsNumber() {
		Class<?> expectedClass = Number.class;
		Object left = (int) 1;
		Object right = (byte) 1;
		Expression e = subtract(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createSubtractExpression_callingToString_getsJavaLikeExpression() {
		Expression e = subtract(1, 2);
		assertThat(e.toString(), is("1 - 2"));
	}
}
