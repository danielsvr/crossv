package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.greaterThan;
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

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		greaterThan(new Object(), new Object());
	}

	@Test
	public void createGraterThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThan(1, 2);
		assertThat(e.toString(), is("1 > 2"));
	}
}