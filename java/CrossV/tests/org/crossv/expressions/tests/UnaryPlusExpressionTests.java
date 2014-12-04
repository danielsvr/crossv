package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.plus;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.UnaryPlus;
import org.junit.Test;

public class UnaryPlusExpressionTests {

	@Test
	public void createPlus_ContextValueAndCallingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Double.class));
		assertThat(e.toString(), is("+context"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createPlusStringExpression_ThrowsIllegalOperandException() {
		plus(constant("string"));
	}

	@Test
	public void createPlusExpression_NumberValue_PreservesTheReturnClass() {
		Class<?> expectedClass = Float.class;
		Expression e = plus((float) 1);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createPlusExpression_PositiveNumberAndCallingToString_getsJavaLikeExpression() {
		Expression e = plus((float) 1);
		assertThat(e.toString(), is("+1.0"));
	}

	@Test
	public void createPlusExpression_NegativeNumberAndCallingToString_getsJavaLikeExpression() {
		Expression e = plus((int) -1);
		assertThat(e.toString(), is("+(-1)"));
	}

	@Test
	public void parseUnaryPlusExpression_Plus1_OperandIs1() {
		UnaryPlus e = UnaryPlus.parse("+1");
		Constant constant = (Constant) e.getOperand();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void evaluateParsedUnaryPlusdExpression_Plus1_ReturnsMinus1()
			throws Exception {
		Expression e = UnaryPlus.parse("+1");
		assertThat(e.evaluate(), is(equalTo(1)));
	}

	@Test
	public void evaluateParsedUnaryPlusdExpression_PlusPlus1_ReturnsMinus1()
			throws Exception {
		Expression e = UnaryPlus.parse("++1");
		assertThat(e.evaluate(), is(equalTo(1)));
	}

	@Test
	public void evaluatePlusExpression_PositiveValue_ReturnsPositiveValue()
			throws Exception {
		float value = 1;
		Expression e = plus(value);
		assertThat(e.evaluate(), is(equalTo(1f)));
	}

	@Test
	public void evaluatePlusExpression_NegativeValue_ReturnsNegativeValue()
			throws Exception {
		int value = -1;
		Expression e = plus(value);
		assertThat(e.evaluate(), is(equalTo(-1)));
	}

	@Test
	public void evaluatePlusExpression_PositoveLongValue_ReturnsPositiveLongValue()
			throws Exception {
		long value = 1;
		Expression e = plus(value);
		assertThat(e.evaluate(), is(equalTo(1L)));
	}

	@Test
	public void evaluatePlusExpression_NegativeDoubleValue_ReturnsNegativeDoubleValue()
			throws Exception {
		double value = -1;
		Expression e = plus(value);
		assertThat(e.evaluate(), is(equalTo(-1d)));
	}
}
