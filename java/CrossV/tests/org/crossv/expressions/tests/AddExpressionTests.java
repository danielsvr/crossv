package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.add;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Add;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.ObjectToString;
import org.junit.Test;

public class AddExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAddExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		add(1, false);
	}

	@Test
	public void createAddExpression_StringAndObjectOperands_ReturnClassIsString() {
		Class<?> expectedClass = String.class;
		Object left = "1";
		Object right = new Object();
		Expression e = add(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAddExpression_ObjectAndStringOperands_ReturnClassIsString() {
		Class<?> expectedClass = String.class;
		Object left = new Object();
		Object right = "1";
		Expression e = add(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAddExpression_NumberOperands_ReturnClassIsNumber() {
		Class<?> expectedClass = Number.class;
		Object left = (int) 1;
		Object right = (byte) 1;
		Expression e = add(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAddExpression_callingToString_getsJavaLikeExpression() {
		Expression e = add(1, 2);
		assertThat(e.toString(), is("1 + 2"));
	}

	@Test
	public void parseAddExpression_1Plus2_LeftConstantIs1() {
		try {
			Add e = Add.parse("1 + 2");
			Constant constant = (Constant) e.getLeft();
			assertThat(constant.getValue(), is(equalTo(1)));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parseAddExpression_1Plus2_RightConstantIs2() {
		Add e = Add.parse("1 + 2");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void evaluatingAParsedAddExpression_1Plus2_Retuns3()
			throws Exception {
		Expression e = Add.parse("1 + 2");
		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void evaluateAddExpression_TwoIntegerValues_ReturnsAddedValuesAsInteger()
			throws Exception {
		int left = 1;
		int right = 2;
		int expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}

	@Test
	public void evaluateAddExpression_IntAndLongValues_ReturnsAddedValuesAsLong()
			throws Exception {
		int left = 1;
		long right = 2;
		long expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}

	@Test
	public void evaluateAddExpression_FloatAndIntValues_ReturnsAddedValuesAsFloat()
			throws Exception {
		float left = 1;
		int right = 2;
		float expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}

	@Test
	public void evaluateAddExpression_LongAndDoubleValues_ReturnsAddedValuesAsDouble()
			throws Exception {
		long left = 1;
		double right = 2;
		double expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}

	@Test
	public void evaluateAddExpression_StringAndObjectValues_ReturnsConcatenatedStrings()
			throws Exception {
		String left = "my ";
		Object right = new ObjectToString("object");
		String expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}

	@Test
	public void evaluateAddExpression_StringAndNullValues_ReturnsConcatenatedStrings()
			throws Exception {
		String left = "my ";
		Object right = null;
		String expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}

	@Test
	public void evaluateAddExpression_NullAndStringValues_ReturnsConcatenatedStrings()
			throws Exception {
		Object left = null;
		String right = " my";
		String expected = left + right;

		Expression e = add(left, right);
		assertThat(e.evaluate(), is(equalTo(expected)));
	}
}
