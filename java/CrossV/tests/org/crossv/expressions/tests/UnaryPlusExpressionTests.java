package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.plus;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class UnaryPlusExpressionTests {

	@Test
	public void createPlus_ContextValueAndCallingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Double.class));
		assertThat(e.toString(), is("+context"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createPlusStringExpression_ThrowsIllegalOperandException() {
		plus(constant("string"));
	}

	@Test
	public void createPlusExpression_NumberValue_PreservesTheReturnClass() {
		Class<?> expectedClass = Float.class;
		Expression e = plus((float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createPlusExpression_PositiveNumberAndCallingToString_getsJavaLikeExpression() {
		Expression e = plus((float) 1);
		assertThat(e.toString(), is("+1.0"));
	}

	@Test
	public void createPlusExpression_NegativeNumberAndCallingToString_getsJavaLikeExpression() {
		Expression e = plus((int) -1);
		assertThat(e.toString(), is("+(-1)"));
	}
}
