package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.lessThan;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.LessThan;
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
	public void parseLessThanExpression_2And1_LeftConstantIs2() {
		LessThan e = LessThan.parse("2 < 1");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void parseLessThanExpression_2And1_RightConstantIs1() {
		LessThan e = LessThan.parse("2 < 1");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void evaluatingAParsedLessThanExpression_2And1_RetunsFalse()
			throws Exception {
		Expression e = LessThan.parse("2 < 1");
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluatingAParsedLessThanExpression_1And2_RetunsTrue()
			throws Exception {
		Expression e = LessThan.parse("1 < 2");
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluatingAParsedLessThanExpression_1And1_RetunsFalse()
			throws Exception {
		Expression e = LessThan.parse("1 < 1");
		assertThat(e.evaluate(), is(equalTo(false)));
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
	public void evaluateLessThanExpression_IntLeftEqualToIntRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2, 2);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanExpression_IntLeftGreaterThanIntRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2, 1);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanExpression_IntLeftEqualToFloatRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2, 2f);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateLessThanExpression_FloatLeftGreaterThanIntRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2f, 1);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
	
	@Test
	public void evaluateLessThanExpression_IntLeftGreaterThanLongRight_ReturnsFalse()
			throws Exception {
		Expression e = lessThan(2, 1L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
}
