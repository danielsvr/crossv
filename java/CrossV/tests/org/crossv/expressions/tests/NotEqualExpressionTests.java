package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.notEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class NotEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createNotEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		notEqual(1, "2");
	}

	@Test
	public void createNotEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = notEqual(1, 2);
		assertThat(e.toString(), is("1 != 2"));
	}
}
