package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.cast;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.Rat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CastExpressionTests {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void createCastOfValueExpression_ReturnClassIsSameAsProvided()
			throws Exception {
		Expression e = cast(Byte.class, 123);
		assertThat(e.getResultClass(), is(assignableTo(Byte.class)));
	}

	@Test
	public void createCastOfContextValueExpression_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = cast(String.class, context());
		assertThat(e.toString(), is("(java.lang.String)context"));
	}

	@Test
	public void evaluateCastExpression_CharToCharClass_ReturnsCharValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Character.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((char) value)));
	}

	@Test
	public void evaluateCastExpression_CharToBooleanClass_EvaluationExceptionIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Cannot cast a char value to boolean");

		char value = 0;
		Expression e = cast(Boolean.class, constant(value));
		e.evaluate();
	}

	@Test
	public void evaluateCastExpression_CharToByte_ReturnsByteValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Byte.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((byte) value)));
	}

	@Test
	public void evaluateCastExpression_CharToShort_ReturnsShortValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Short.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((short) value)));
	}

	@Test
	public void evaluateCastExpression_CharToInt_ReturnsIntValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Integer.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((int) value)));
	}

	@Test
	public void evaluateCastExpression_CharToLong_ReturnsLongValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Long.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((long) value)));
	}

	@Test
	public void evaluateCastExpression_CharToFloat_ReturnsFloatValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Float.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((float) value)));
	}

	@Test
	public void evaluateCastExpression_CharToDouble_ReturnsDoubleValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Double.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((double) value)));
	}
	
	@Test
	public void evaluateCastExpression_CharToNumberType_ReturnsDoubleValue()
			throws Exception {
		char value = 0;
		Expression e = cast(Double.TYPE, constant(value));
		assertThat(e.evaluate(), is(equalTo((double) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToNumberType_ReturnsCharValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Double.TYPE, constant(value));
		assertThat(e.evaluate(), is(equalTo((double) value)));
	}
	
	@Test
	public void evaluateCastExpression_NumberToChar_ReturnsCharValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Character.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((char) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToBoolean_EvaluationExceptionIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Cannot cast a byte value to boolean");

		byte value = 0;
		Expression e = cast(Boolean.class, constant(value));
		e.evaluate();
	}

	@Test
	public void evaluateCastExpression_NumberToByte_ReturnsByteValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Byte.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((byte) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToShort_ReturnsShortValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Short.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((short) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToInt_ReturnsIntValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Integer.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((int) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToLong_ReturnsLongValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Long.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((long) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToFloat_ReturnsFloatValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Float.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((float) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToDouble_ReturnsDoubleValue()
			throws Exception {
		byte value = 0;
		Expression e = cast(Double.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((double) value)));
	}

	@Test
	public void evaluateCastExpression_NumberToObject_ReturnsObjectValue()
			throws Exception {
		double value = 0;
		Expression e = cast(Object.class, constant(value));
		assertThat(e.evaluate(), is(equalTo((double) value)));
	}

	@Test
	public void evaluateCastExpression_BooleanToNumber_EvaluationExceptionIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Cannot cast a boolean value to int");

		boolean value = true;
		Expression e = cast(Integer.class, constant(value));
		e.evaluate();
	}

	@Test
	public void evaluateCastExpression_BooleanToChar_EvaluationExceptionIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Cannot cast a boolean value to char");

		boolean value = true;
		Expression e = cast(Character.class, constant(value));
		e.evaluate();
	}

	@Test
	public void evaluateCastExpression_BooleanToBoolean_ReturnsBooleanValue()
			throws Exception {
		boolean value = true;
		Expression e = cast(Boolean.class, constant(value));
		assertThat(e.evaluate(), is(equalTo(value)));
	}

	@Test
	public void evaluateCastExpression_BooleanToObject_ReturnsObjectValue()
			throws Exception {
		boolean value = true;
		Expression e = cast(Object.class, constant(value));
		assertThat(e.evaluate(), is(equalTo(value)));
	}

	@Test
	public void evaluateCastExpression_RatToMouse_ReturnsMouseValue()
			throws Exception {
		Rat value = new Rat();
		Expression e = cast(Mouse.class, constant(value));
		assertThat(e.evaluate(), is(equalTo(value)));
	}

	@Test
	public void evaluateCastExpression_MouseToRat_EvaluationExceptionIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Cannot cast a "
				+ "org.crossv.tests.subjects.Mouse value to "
				+ "org.crossv.tests.subjects.Rat");

		Mouse value = new Mouse();
		Expression e = cast(Rat.class, constant(value));
		e.evaluate();
	}
}
