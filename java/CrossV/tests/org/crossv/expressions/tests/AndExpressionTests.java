package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.bitwiseAnd;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.And;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AndExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_BooleanAndIntOperands_ThrowsIllegalOperandException() {
		bitwiseAnd(constant(false), 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_DoubleAndBooleanOperands_ThrowsIllegalOperandException() {
		bitwiseAnd((double) 1, constant(false));
	}

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_DoubleAndByteOperands_ThrowsIllegalOperandException() {
		bitwiseAnd((double) 1, (byte) 1);
	}

	@Test
	public void createAndExpression_BooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean left = false;
		boolean right = true;
		Expression e = bitwiseAnd(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_NumericOperandsPromotedToLong_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (byte) 1;
		Object right = (long) 1;
		Expression e = bitwiseAnd(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_NumericOperandsPromotedToInt_ReturnClassIsInt() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = bitwiseAnd(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		Expression e = bitwiseAnd(1, 2);
		assertThat(e.toString(), is("1 & 2"));
	}

	@Test
	public void parseAndExpression_1And2_LeftConstantIs1() {
		And e = And.parse("1 & 2");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void parseAndExpression_1And2_RightConstantIs2() {
		And e = And.parse("1 & 2");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void evaluatingAParsedAndExpression_1And2_Retuns0() throws Exception {
		Expression e = And.parse("1 & 2");
		assertThat(e.evaluate(), is(equalTo(0)));
	}

	@Test
	public void evaluateAndExpression_OneAndTwoAsIntegerValues_ReturnsZeroAsInteger()
			throws Exception {
		int left = 1;
		int right = 2;

		Expression e = bitwiseAnd(left, right);
		assertThat(e.evaluate(), is(equalTo(0)));
	}

	@Test
	public void evaluateAndExpression_OneAsIntAndTwoAsLongValues_ReturnsZeroAsLong()
			throws Exception {
		int left = 1;
		long right = 2;

		Expression e = bitwiseAnd(left, right);
		assertThat(e.evaluate(), is(equalTo(0L)));
	}

	@Test
	public void evaluateAndExpression_FalseAndTrue_ReturnsFalse()
			throws Exception {
		boolean left = false;
		boolean right = true;
		Expression e = bitwiseAnd(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateAndExpression_FalseAndFalse_ReturnsFalse()
			throws Exception {
		boolean left = false;
		boolean right = false;
		Expression e = bitwiseAnd(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateAndExpression_TrueAndTrue_ReturnsFalse()
			throws Exception {
		boolean left = true;
		boolean right = true;
		Expression e = bitwiseAnd(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}
}
