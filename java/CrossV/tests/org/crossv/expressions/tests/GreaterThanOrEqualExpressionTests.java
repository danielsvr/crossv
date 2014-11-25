package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.greaterThanOrEqual;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class GreaterThanOrEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanOrEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		greaterThanOrEqual(1, "2");
	}

	@Test
	public void createGreaterThanOrEqualExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		Object left = 1;
		Object right = 2;
		Expression e = greaterThanOrEqual(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createGraterThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThanOrEqual(1, 2);
		assertThat(e.toString(), is("1 >= 2"));
	}
	

	@Test
	public void evaluateGraterThanOrEqualExpression_LeftGreaterThatRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2, 1);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftGreaterThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2, 1L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_FloatLeftGreaterThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2f, 1L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_DoubleLeftGreaterThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2d, 1L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_DoubleLeftEqualToLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2d, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftLessThanLongRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThanOrEqual(1, 2L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
}
