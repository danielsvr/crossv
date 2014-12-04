package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.or;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.OrElse;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class OrElseExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_nonBooleanFirstOperands_IllegalOperandExceptionIsThrown() {
		or(constant(1), constant(true));
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_nonBooleanSecondOperands_IllegalOperandExceptionIsThrown() {
		or(constant(true), constant(2));
	}

	@Test
	public void createOrExpression_BooleanAndBooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = true;
		boolean right = false;
		Expression e = or(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createOrExpression_callingToString_getsJavaLikeExpression() {
		boolean left = true;
		boolean right = false;
		Expression e = or(left, right);
		assertThat(e.toString(), is("true || false"));
	}
	
	@Test
	public void parseOrElseExpression_TrueAndFalse_LeftConstantIsTrue() {
		OrElse e = OrElse.parse("true || false");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(true)));
	}

	@Test
	public void parseOrElseExpression_TrueAndFalse_RightConstantIsFalse() {
		OrElse e = OrElse.parse("true || false");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(false)));
	}

	@Test
	public void evaluatingAParsedOrElseExpression_TrueAndFalse_RetunsTrue()
			throws Exception {
		Expression e = OrElse.parse("true || false");
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateOrExpression_FalseAndTrue_ReturnsTrue()
			throws Exception {
		boolean left = false;
		boolean right = true;
		Expression e = or(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateOrExpression_FalseAndFalse_ReturnsFalse()
			throws Exception {
		boolean left = false;
		boolean right = false;
		Expression e = or(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateOrExpression_TrueAndTrue_ReturnsTrue() throws Exception {
		boolean left = true;
		boolean right = true;
		Expression e = or(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}
}
