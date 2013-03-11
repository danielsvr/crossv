package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.lessThan;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
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
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createLessThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThan(1, 2);
		assertThat(e.toString(), is("1 < 2"));
	}

	@Test
	public void evaluateLessThanExpression_LeftLessThatRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThan(1, 2);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanExpression_IntLeftLessThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThan(1, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanExpression_FloatLeftLessThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThan(1f, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanExpression_DoubleLeftLessThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThan(1d, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanExpression_DoubleLeftEqualToLongRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2d, 2L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanExpression_IntLeftGreaterThanLongRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2, 1L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
}
