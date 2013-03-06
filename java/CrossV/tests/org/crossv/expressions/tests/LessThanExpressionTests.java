package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
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

	@Test
	public void createLessThanExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		int left = 1;
		int right = 2;
		Expression e = lessThan(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createLessThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThan(1, 2);
		assertThat(e.toString(), is("1 < 2"));
	}
}
