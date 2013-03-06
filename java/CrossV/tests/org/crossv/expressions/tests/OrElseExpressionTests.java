package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.or;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class OrElseExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_nonBooleanFirstOperands_IllegalOperandExceptionIsThrown() {
		or(constant(1), constant(true));
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_nonBooleanSecondOperands_IllegalOperandExceptionIsThrown() {
		or(constant(true), constant(2));
	}

	@Test
	public void createOrExpression_BooleanAndBooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = true;
		boolean right = false;
		Expression e = or(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(left));
	}

	@Test
	public void createOrExpression_callingToString_getsJavaLikeExpression() {
		boolean left = true;
		boolean right = false;
		Expression e = or(left, right);
		assertThat(e.toString(), is("true || false"));
	}
}
