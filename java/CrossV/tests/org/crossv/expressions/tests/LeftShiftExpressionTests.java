package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.leftShift;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.LeftShift;
import org.junit.Test;

public class LeftShiftExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		leftShift(1, false);
	}

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_BooleanAndIntOperands_ThrowsIllegalOperandException() {
		leftShift(true, 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_IntAndDoubleOperands_ThrowsIllegalOperandException() {
		leftShift(1, (double) 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createLeftShiftExpression_FloatAndIntOperands_ThrowsIllegalOperandException() {
		leftShift((float) 1, 1);
	}

	@Test
	public void createLeftShiftExpression_ByteAndIntOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = leftShift(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createLeftShiftExpression_LongAndIntOperands_ReturnClassIsLong() {
		Class<?> expectedClass = Long.class;
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = leftShift(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createLeftShiftExpression_IntAndLongOperands_ReturnClassIsInteger() {
		Class<?> expectedClass = Integer.class;
		Object left = (int) 1;
		Object right = (long) 1;
		Expression e = leftShift(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createLeftShiftExpression_callingToString_getsJavaLikeExpression() {
		Expression e = leftShift(1, 2);
		assertThat(e.toString(), is("1 << 2"));
	}

	@Test
	public void parseLeftShiftExpression_1And2_LeftConstantIs1() {
		LeftShift e = LeftShift.parse("1 << 2");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void parseLeftShiftExpression_1And2_RightConstantIs2() {
		LeftShift e = LeftShift.parse("1 << 2");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void evaluatingAParsedLeftShiftExpression_1And2_Retuns4()
			throws Exception {
		Expression e = LeftShift.parse("1 << 2");
		assertThat(e.evaluate(), is(equalTo(4)));
	}

	@Test
	public void evaluateLeftShiftExpression_OneByteAndOneIntValue_ReturnsTwo()
			throws Exception {
		Object left = (byte) 1;
		Object right = (int) 1;
		Expression e = leftShift(left, right);
		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateLeftShiftExpression_OneLongAndOneIntValue_ReturnsTwo()
			throws Exception {
		Object left = (long) 1;
		Object right = (int) 1;
		Expression e = leftShift(left, right);
		assertThat(e.evaluate(), is(equalTo(2L)));
	}

	@Test
	public void evaluateLeftShiftExpression_OneIntAndOneLongValue_ReturnsTwo()
			throws Exception {
		Object left = (int) 1;
		Object right = (long) 1;
		Expression e = leftShift(left, right);
		assertThat(e.evaluate(), is(equalTo(2)));
	}
}
