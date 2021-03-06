package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.and;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.AndAlso;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AndAlsoExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanFirstOperands_IllegalOperandExceptionIsThrown() {
		and(constant(1), constant(true));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanSecondOperands_IllegalOperandExceptionIsThrown() {
		and(constant(true), constant(2));
	}

	@Test
	public void createAndExpression_BooleanAndBooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = true;
		boolean right = false;
		Expression e = and(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		boolean left = true;
		boolean right = false;
		Expression e = and(left, right);
		assertThat(e.toString(), is("true && false"));
	}

	@Test
	public void parseAndAlsoExpression_TrueAndFalse_LeftConstantIsTrue() {
		AndAlso e = AndAlso.parse("true && false");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(true)));
	}

	@Test
	public void parseAndAlsoExpression_TrueAndFalse_RightConstantIsFalse() {
		AndAlso e = AndAlso.parse("true && false");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(false)));
	}

	@Test
	public void evaluatingAParsedAndAlsoExpression_TrueAndFalse_RetunsFalse()
			throws Exception {
		Expression e = AndAlso.parse("true && false");
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateAndExpression_FalseAndTrue_ReturnsFalse()
			throws Exception {
		boolean left = false;
		boolean right = true;
		Expression e = and(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateAndExpression_FalseAndFalse_ReturnsFalse()
			throws Exception {
		boolean left = false;
		boolean right = false;
		Expression e = and(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateAndExpression_TrueAndTrue_ReturnsTrue()
			throws Exception {
		boolean left = true;
		boolean right = true;
		Expression e = and(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}
}
