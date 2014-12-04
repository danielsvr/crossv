package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.lessThanOrEqual;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.LessThanOrEqual;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class LessThanOrEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createLessThanOrEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		lessThanOrEqual(1, "2");
	}

	@Test
	public void createLessThanOrEqualExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		int left = 1;
		int right = 2;
		Expression e = lessThanOrEqual(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createLessThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThanOrEqual(1, 2);
		assertThat(e.toString(), is("1 <= 2"));
	}

	@Test
	public void parseLessThanOrEqualExpression_2And1_LeftConstantIs2() {
		LessThanOrEqual e = LessThanOrEqual.parse("2 <= 1");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void parseLessThanOrEqualExpression_2And1_RightConstantIs1() {
		LessThanOrEqual e = LessThanOrEqual.parse("2 <= 1");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void evaluatingAParsedLessThanOrEqualExpression_2And1_RetunsFalse()
			throws Exception {
		Expression e = LessThanOrEqual.parse("2 <= 1");
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluatingAParsedLessThanOrEqualExpression_1And2_RetunsTrue()
			throws Exception {
		Expression e = LessThanOrEqual.parse("1 <= 2");
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluatingAParsedLessThanOrEqualExpression_1And1_RetunsTrue()
			throws Exception {
		Expression e = LessThanOrEqual.parse("1 <= 1");
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_LeftLessThatRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(1, 2);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_IntLeftLessThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(1, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_FloatLeftLessThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(1f, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_DoubleLeftLessThatLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(1d, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_DoubleLeftEqualToLongRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(2d, 2L);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_IntLeftEqualToIntRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(2, 2);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_IntLeftLessThanIntRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThanOrEqual(2, 1);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_FloatLeftEqualToIntRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(2f, 2);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_IntLeftLessThanFloatRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThanOrEqual(2, 1f);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_FloatLeftEqualToDoubleRight_ReturnsTrue()
			throws Exception {
		Expression e = lessThanOrEqual(2f, 2d);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_IntLeftLessThanDoubleRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThanOrEqual(2, 1d);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanOrEqualExpression_IntLeftLessThanLongRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThanOrEqual(2, 1L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
}