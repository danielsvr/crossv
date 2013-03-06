package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.conditional;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ConditionalExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createConditionalExpression_NonBooleanTestExpression_throwsIllegalOperandException() {
		conditional(constant(new Object()), constant(new Object()),
				constant(new Object()));
	}

	@Test(expected = IllegalOperandException.class)
	public void createConditionalExpression_BooleanTestAndNullForRestOfOperands_throwsIllegalOperandException() {
		conditional(constant(true), constant(null), constant(null));
	}

	@Test
	public void createConditionalExpression_SecondIsDoubleAndThirdIsStringExpression_ReturnsMostBaseClass() {
		Object second = (double) 1;
		Object third = "2";
		Class<?> expectedClass = Object.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_SecondIsIntAndThirdIsStringExpression_ReturnsMostBaseClass() {
		Object second = (int) 1;
		Object third = "2";
		Class<?> expectedClass = Object.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_SecondIsShortAndThirdIsStringExpression_ReturnsMostBaseClass() {
		Object second = (short) 1;
		Object third = "2";
		Class<?> expectedClass = Object.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_SecondIsByteAndThirdIsStringExpression_ReturnsMostBaseClass() {
		Object second = (byte) 1;
		Object third = "2";
		Class<?> expectedClass = Object.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondAndThirdOperandsHaveTheSameType_ReturnsThatType() {
		Object second = new Monkey();
		Object third = new Monkey();
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondAndThirdOperandsIsOfTypeBoolean_ReturnsBoolean() {
		Object second = true;
		Object third = false;
		Class<?> expectedClass = Boolean.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsNullAndThirdIsAReferenceType_ReturnsThatReferenceType() {
		Object second = null;
		Object third = new Monkey();
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsAReferenceTypeIsAndThirdNull_ReturnsThatReferenceType() {
		Object second = new Monkey();
		Object third = null;
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsShortAndThirdIsInt_ReturnsShortType() {
		Object second = (short) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsShort_ReturnsShortType() {
		Object second = (int) 1;
		Object third = (short) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsShortAndThirdIsByte_ReturnsShortType() {
		Object second = (short) 1;
		Object third = (byte) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsByteAndThirdIsShort_ReturnsShortType() {
		Object second = (byte) 1;
		Object third = (short) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsCharAndThirdIsIntReturnsByteType() {
		Object second = (char) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Integer.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsCharReturnsByteType() {
		Object second = (int) 1;
		Object third = (char) 1;
		Class<?> expectedClass = Integer.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsByteAndThirdIsInt_ReturnsByteType() {
		Object second = (byte) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Byte.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsByte_ReturnsByteType() {
		Object second = (int) 1;
		Object third = (byte) 1;
		Class<?> expectedClass = Byte.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsFloatAndThirdIsByte_ReturnsDoubleType() {
		Object second = (float) 1;
		Object third = (byte) 1;
		Class<?> expectedClass = Float.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsByteAndThirdIsFloat_ReturnsDoubleType() {
		Object second = (byte) 1;
		Object third = (float) 1;
		Class<?> expectedClass = Float.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsDoubleAndThirdIsInt_ReturnsDoubleType() {
		Object second = (double) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Double.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsDouble_ReturnsDoubleType() {
		Object second = (int) 1;
		Object third = (double) 1;
		Class<?> expectedClass = Double.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsDoubleAndThirdIsShort_ReturnsDoubleType() {
		Object second = (double) 1;
		Object third = (short) 1;
		Class<?> expectedClass = Double.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsShortAndThirdIsDouble_ReturnsDoubleType() {
		Object second = (short) 1;
		Object third = (double) 1;
		Class<?> expectedClass = Double.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_callingToString_getsJavaLikeExpression() {
		Expression e = conditional(true, 2, 3);
		assertThat(e.toString(), is("true ? 2 : 3"));
	}
}
