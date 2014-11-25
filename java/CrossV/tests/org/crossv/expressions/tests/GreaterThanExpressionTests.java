package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.greaterThan;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class GreaterThanExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createGreaterThanExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		greaterThan(1, "2");
	}

	@Test
	public void createGreaterThanExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		int left = 1;
		int right = 2;
		Expression e = greaterThan(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createGreaterThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThan(1, 2);
		assertThat(e.toString(), is("1 > 2"));
	}

	@Test
	public void evaluateGreaterThanExpression_LeftGreaterThatRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThan(2, 1);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGreaterThanExpression_IntLeftGreaterThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThan(2, 1L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGreaterThanExpression_FloatLeftGreaterThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThan(2f, 1L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGreaterThanExpression_DoubleLeftGreaterThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThan(2d, 1L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGreaterThanExpression_DoubleLeftEqualToLongRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThan(2d, 2L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateGreaterThanExpression_IntLeftLessThanLongRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThan(1, 2L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
}