package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.greaterThanOrEqual;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.GreaterThanOrEqual;
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
	public void parseGreaterThanOrEqualExpression_1And2_LeftConstantIs1() {
		GreaterThanOrEqual e = GreaterThanOrEqual.parse("1 >= 2");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void parseGreaterThanOrEqualExpression_1And2_RightConstantIs2() {
		GreaterThanOrEqual e = GreaterThanOrEqual.parse("1 >= 2");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void evaluatingAParsedGreaterThanOrEqualExpression_1And2_RetunsFalse()
			throws Exception {
		Expression e = GreaterThanOrEqual.parse("1 >= 2");
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluatingAParsedGreaterThanOrEqualExpression_2And1_RetunsTrue()
			throws Exception {
		Expression e = GreaterThanOrEqual.parse("2 >= 1");
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluatingAParsedGreaterThanOrEqualExpression_1And1_RetunsTrue()
			throws Exception {
		Expression e = GreaterThanOrEqual.parse("1 >= 1");
		assertThat(e.evaluate(), is(equalTo(true)));
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
	public void evaluateGraterThanOrEqualExpression_IntLeftEqualToIntRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2, 2);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftLessThanIntRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThanOrEqual(1, 2);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftEqualToFloatRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2, 2f);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftLessThanFloatRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThanOrEqual(1, 2f);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftEqualToDoubleRight_ReturnsTrue()
			throws Exception {
		Expression e = greaterThanOrEqual(2, 2d);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftLessThanDoubleRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThanOrEqual(1, 2d);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateGraterThanOrEqualExpression_IntLeftLessThanLongRight_ReturnsFalse()
			throws Exception {
		Expression e = greaterThanOrEqual(1, 2L);
		assertThat(e.evaluate(), is(equalTo(false)));
	}
}
