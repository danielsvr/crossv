package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.conditional;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.ConditionalTernary;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ConditionalExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createConditionalExpression_NonBooleanTestExpression_throwsIllegalOperandException() {
		conditional(constant(new Object()), constant(new Object()),
				constant(new Object()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createConditionalExpression_NullTestExpression_ThrowsIllegalArgumentException() {
		conditional(null, constant(new Object()), constant(new Object()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createConditionalExpression_NullIfTrueExpression_ThrowsIllegalArgumentException() {
		conditional(constant(new Object()), null, constant(new Object()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createConditionalExpression_NullIfFalseExpression_ThrowsIllegalArgumentException() {
		conditional(constant(new Object()), constant(new Object()), null);
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
		Object second = TestObjectFactory.createMonkey();
		Object third = TestObjectFactory.createMonkey();
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
		Object third = TestObjectFactory.createMonkey();
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createConditionalExpression_TheSecondIsAReferenceTypeIsAndThirdNull_ReturnsThatReferenceType() {
		Object second = TestObjectFactory.createMonkey();
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

	@Test
	public void parseConditionalExpression_TestTrue2IfTrue3IfFalse_TestConstantIsTrue() {
		ConditionalTernary e = ConditionalTernary.parse("true ? 2 : 3");
		Constant constant = (Constant) e.getTest();
		assertThat(constant.getValue(), is(equalTo(true)));
	}

	@Test
	public void parseConditionalExpression_TestTrue2IfTrue3IfFalse_IfTrueConstantIs2() {
		ConditionalTernary e = ConditionalTernary.parse("true ? 2 : 3");
		Constant constant = (Constant) e.getIfTrue();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void parseConditionalExpression_TestTrue2IfTrue3IfFalse_IfFalseConstantIs3() {
		ConditionalTernary e = ConditionalTernary.parse("true ? 2 : 3");
		Constant constant = (Constant) e.getIfFalse();
		assertThat(constant.getValue(), is(equalTo(3)));
	}

	@Test
	public void evaluatingAParsedConditionalExpression_TestTrue2IfTrue3IfFalse_Retuns2()
			throws Exception {
		Expression e = ConditionalTernary.parse("true ? 2 : 3");
		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateConditionalExpression_TestIsTrue_ReturnsIfTrueValue()
			throws Exception {
		Expression e = conditional(true, 2, 3);
		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateConditionalExpression_TestIsFalse_ReturnsIfFalseValue()
			throws Exception {
		Expression e = conditional(false, 2, 3);
		assertThat(e.evaluate(), is(equalTo(3)));
	}
}
