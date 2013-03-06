package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.not;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class NotExpressionTests {

	@Test
	public void createNotExpressionForTrueValue_callingToString_getsJavaLikeExpression() {
		Expression e = not(true);
		assertThat(e.toString(), is("!true"));
	}
	
	@Test(expected = IllegalOperandException.class)
	public void createNotExpressionForNonBooleanValue_ThrowsIllegalOperandException() {
		not(constant(123));
	}
	
	@Test
	public void createNotExpression_BooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean operand = true;
		Expression e = not(operand);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}
}
