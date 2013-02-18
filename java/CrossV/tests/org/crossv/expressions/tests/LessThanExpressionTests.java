package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.lessThan;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class LessThanExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createLessThanExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		lessThan(1, "2");
	}

	@Test(expected = IllegalOperandException.class)
	public void createLessThanExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		lessThan(new Object(), new Object());
	}

	@Test
	public void createLessThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThan(1, 2);
		assertThat(e.toString(), is("(1 < 2)"));
	}
}
