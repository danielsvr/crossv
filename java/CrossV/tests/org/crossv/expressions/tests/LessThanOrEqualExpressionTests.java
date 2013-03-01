package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.lessThanOrEqual;
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

	@Test(expected = IllegalOperandException.class)
	public void createLessThanOrEqualExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		lessThanOrEqual(new Object(), new Object());
	}

	@Test
	public void createLessThanOrEqualExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) (1 <= 2)).getClass();
		Expression e = lessThanOrEqual(1, 2);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLessThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThanOrEqual(1, 2);
		assertThat(e.toString(), is("1 <= 2"));
	}
}