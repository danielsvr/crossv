package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.and;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AndAlsoExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanFirstOperands_IllegalOperandExceptionIsThrown() {
		and(constant(1), constant(true));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanSecondOperands_IllegalOperandExceptionIsThrown() {
		and(constant(true), constant(2));
	}

	@Test
	public void createAndExpression_BooleanAndBooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = true;
		boolean right = false;
		Expression e = and(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		boolean left = true;
		boolean right = false;
		Expression e = and(left, right);
		assertThat(e.toString(), is("true && false"));
	}
}
