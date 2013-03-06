package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.greaterThan;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class GreaterThanExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		greaterThan(1, "2");
	}

	@Test
	public void createGreaterThanExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		int left = 1;
		int right = 2;
		Expression e = greaterThan(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createGraterThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThan(1, 2);
		assertThat(e.toString(), is("1 > 2"));
	}
}