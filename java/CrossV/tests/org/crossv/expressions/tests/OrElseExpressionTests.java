package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.or;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class OrElseExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_nonBooleanOperands_IllegalOperandExceptionIsThrown() {
		or(constant(1), constant(2));
	}

	@Test
	public void createOrExpression_callingToString_getsJavaLikeExpression() {
		Expression e = or(true, false);
		assertThat(e.toString(), is("(true || false)"));
	}
}
