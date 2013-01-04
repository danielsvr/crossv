package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class ExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanOperands_IllegalOperandExceptionIsThrown() {
		and(1, 2);
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		Expression e = and(true, false);
		assertThat(e.toString(), is("(true && false)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		equal(1, "2");
	}

	@Test
	public void createEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = equal(1, 2);
		assertThat(e.toString(), is("(1 == 2)"));
	}

	@Test
	public void combiningAndAndEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = and(false, and(equal(1, 2), equal("a", "b")));
		assertThat(e.toString(),
				is("(false && ((1 == 2) && (\"a\" == \"b\")))"));
	}
}
