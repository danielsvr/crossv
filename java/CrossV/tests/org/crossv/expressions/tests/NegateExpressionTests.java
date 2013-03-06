package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.negate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class NegateExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createNegatedStringExpression_ThrowsIllegalOperandException() {
		negate(constant("string"));
	}

	@Test
	public void createNegatedExpression_NumberValue_PreservesTheReturnClass() {
		Class<?> expectedClass = Float.class;
		Expression e = negate((float) 1);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createNegatedFloatExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((float) 1);
		assertThat(e.toString(), is("-1.0"));
	}

	@Test
	public void createNegatedIntExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((int) -1);
		assertThat(e.toString(), is("-(-1)"));
	}

	@Test
	public void createNegatedDoubleContextValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate(context(Double.class));
		assertThat(e.toString(), is("-context"));
	}
}
