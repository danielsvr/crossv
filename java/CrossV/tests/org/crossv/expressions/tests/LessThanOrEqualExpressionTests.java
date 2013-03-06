package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.lessThanOrEqual;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class LessThanOrEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createLessThanOrEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		lessThanOrEqual(1, "2");
	}

	@Test
	public void createLessThanOrEqualExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		int left = 1;
		int right = 2;
		Expression e = lessThanOrEqual(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createLessThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThanOrEqual(1, 2);
		assertThat(e.toString(), is("1 <= 2"));
	}
}