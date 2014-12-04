package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.subtract;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.Subtract;
import org.junit.Test;

public class SubtractExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createSubtractExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		subtract(1, false);
	}

	@Test
	public void createSubtractExpression_NumberOperands_ReturnClassIsNumber() {
		Class<?> expectedClass = Number.class;
		Object left = (int) 1;
		Object right = (byte) 1;
		Expression e = subtract(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createSubtractExpression_callingToString_getsJavaLikeExpression() {
		Expression e = subtract(1, 2);
		assertThat(e.toString(), is("1 - 2"));
	}

	@Test
	public void parseSubtractExpression_1Minus2_LeftConstantIs1() {
		Subtract e = Subtract.parse("1 - 2");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void parseSubtractExpression_1Minus2_RightConstantIs2() {
		Subtract e = Subtract.parse("1 - 2");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void evaluatingAParsedSubtractExpression_1Minus2_RetunsMinus1()
			throws Exception {
		Expression e = Subtract.parse("1 - 2");
		assertThat(e.evaluate(), is(equalTo(-1)));
	}

	@Test
	public void evaluateSubtractExpression_TwoIntegerValues_ReturnsSubtractedValuesAsInteger()
			throws Exception {
		int left = 1;
		int right = 2;

		Expression e = subtract(left, right);
		assertThat(e.evaluate(), is(equalTo(-1)));
	}

	@Test
	public void evaluateSubtractExpression_IntAndLongValues_ReturnsSubtractedValuesAsLong()
			throws Exception {
		int left = 1;
		long right = 2;

		Expression e = subtract(left, right);
		assertThat(e.evaluate(), is(equalTo(-1L)));
	}

	@Test
	public void evaluateSubtractExpression_FloatAndIntValues_ReturnsSubtractedValuesAsFloat()
			throws Exception {
		float left = 1;
		int right = 2;

		Expression e = subtract(left, right);
		assertThat(e.evaluate(), is(equalTo(-1f)));
	}

	@Test
	public void evaluateSubtractExpression_LongAndDoubleValues_ReturnsSubtractedValuesAsDouble()
			throws Exception {
		long left = 1;
		double right = 2;

		Expression e = subtract(left, right);
		assertThat(e.evaluate(), is(equalTo(-1d)));
	}
}
