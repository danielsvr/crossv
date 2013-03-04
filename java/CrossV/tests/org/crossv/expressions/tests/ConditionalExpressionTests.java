package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.text.MessageFormat.format;

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
		conditional(constant(new Object()), constant(null), constant(null));
	}

	@Test
	public void createConditionalExpression_BooleanTestAndNotSameTypeForSecondAndThirdExpression_ReturnsMostBaseClass() {
		Object second = 1;
		Object third = "2";
		Class<?> expectedClass = Object.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondAndThirdOperandsHaveTheSameType_ReturnsThatType() {
		Object second = new Monkey();
		Object third = new Monkey();
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondAndThirdOperandsIsOfTypeBoolean_ReturnsBoolean() {
		Object second = true;
		Object third = false;
		Class<?> expectedClass = Boolean.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsNullAndThirdIsAReferenceType_ReturnsThatReferenceType() {
		Object second = null;
		Object third = new Monkey();
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsAReferenceTypeAndThirdIsNull_ReturnsThatReferenceType() {
		Object second = null;
		Object third = new Monkey();
		Class<?> expectedClass = Monkey.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsByteAndThirdIsByte_ReturnsByteType() {
		Object second = (byte) 1;
		Object third = (byte) 1;
		Class<?> expectedClass = Byte.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsShortAndThirdIsInt_ReturnsShortType() {
		Object second = (short) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsShort_ReturnsShortType() {
		Object second = (int) 1;
		Object third = (short) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsShortAndThirdIsByte_ReturnsShortType() {
		Object second = (short) 1;
		Object third = (byte) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsByteAndThirdIsShort_ReturnsShortType() {
		Object second = (byte) 1;
		Object third = (short) 1;
		Class<?> expectedClass = Short.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsCharAndThirdIsIntReturnsByteType() {
		Object second = (char) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Character.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsCharReturnsByteType() {
		Object second = (int) 1;
		Object third = (char) 1;
		Class<?> expectedClass = Character.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsByteAndThirdIsInt_ReturnsByteType() {
		Object second = (byte) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Byte.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsByte_ReturnsByteType() {
		Object second = (int) 1;
		Object third = (byte) 1;
		Class<?> expectedClass = Byte.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsDoubleAndThirdIsInt_ReturnsDoubleType() {
		Object second = (double) 1;
		Object third = (int) 1;
		Class<?> expectedClass = Double.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_TheSecondIsIntAndThirdIsDouble_ReturnsDoubleType() {
		Object second = (int) 1;
		Object third = (double) 1;
		Class<?> expectedClass = Double.class;
		Expression e = conditional(true, second, third);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createConditionalExpression_callingToString_getsJavaLikeExpression() {
		Expression e = conditional(true, 2, 3);
		assertThat(e.toString(), is("true ? 2 : 3"));
	}
}
