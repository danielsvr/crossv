package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.greaterThanOrEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class GreaterThanOrEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanOrEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		greaterThanOrEqual(1, "2");
	}

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanOrEqualExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		greaterThanOrEqual(new Object(), new Object());
	}

	@Test
	public void createGraterThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThanOrEqual(1, 2);
		assertThat(e.toString(), is("(1 >= 2)"));
	}
}
