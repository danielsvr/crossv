package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.equal;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class EqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		equal(1, "2");
	}

	@Test
	public void createEqualExpression_IntAndIntOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) (1 == 2)).getClass();
		Expression e = equal(1, 2);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = equal(1, 2);
		assertThat(e.toString(), is("1 == 2"));
	}
}
