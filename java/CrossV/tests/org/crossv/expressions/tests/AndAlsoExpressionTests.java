package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.and;
import static org.crossv.expressions.Expression.constant;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AndAlsoExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanOperands_IllegalOperandExceptionIsThrown() {
		and(constant(1), constant(2));
	}

	@Test
	public void createAndExpression_BooleanAndBooleanOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) (true && false)).getClass();
		Expression e = and(true, false);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		Expression e = and(true, false);
		assertThat(e.toString(), is("true && false"));
	}
}
