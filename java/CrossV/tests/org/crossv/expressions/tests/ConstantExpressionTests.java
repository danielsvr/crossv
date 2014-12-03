package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.primitives.Iterables.repeat;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class ConstantExpressionTests {

	@Test
	public void createConstantWithNullValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant(null);
		assertThat(e.toString(), is("null"));
	}

	@Test
	public void createConstantWithStringValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant("abc");
		assertThat(e.toString(), is("\"abc\""));
	}

	@Test
	public void createConstantWithNegativeByteValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((byte) -1);
		assertThat(e.toString(), is("(-1)"));
	}

	@Test
	public void createConstantWithPositiveFloatValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((float) 1);
		assertThat(e.toString(), is("1.0"));
	}

	@Test
	public void createConstantExpressionWithIterableOfFloats_callingToString_getsJavaLikeExpression() {
		Expression e = constant(repeat((float) 1, 3));
		assertThat(e.toString(), is("new java.lang.Float[] { 1.0, 1.0, 1.0 }"));
	}

	@Test
	public void parseConstantExpression_NullValue_TheValueIsNull() {
		Constant e = Constant.parse("null");
		Object constant = e.getValue();
		assertThat(constant, is(nullValue()));
	}

	@Test
	public void parseConstantExpression_AbcStringValue_TheValueIsAbc() {
		Constant e = Constant.parse("\"abc\"");
		Object constant = e.getValue();
		assertThat(constant, is(equalTo("abc")));
	}

	@Test
	public void parseConstantExpression_123IntValue_TheValueIs123() {
		Constant e = Constant.parse("123");
		Object constant = e.getValue();
		assertThat(constant, is(equalTo(123)));
	}

	@Test
	public void parseConstantExpression_123IntValue_TheValueIsInt() {
		Constant e = Constant.parse("123");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Integer.class)));
	}

	@Test
	public void parseConstantExpression_123lLongValue_TheValueIsLong() {
		Constant e = Constant.parse("123l");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Long.class)));
	}

	@Test
	public void parseConstantExpression_123LLongValue_TheValueIsLong() {
		Constant e = Constant.parse("123L");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Long.class)));
	}

	@Test
	public void parseConstantExpression_123fFloatValue_TheValueIsFloat() {
		Constant e = Constant.parse("123f");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Float.class)));
	}

	@Test
	public void parseConstantExpression_123dDoubleValue_TheValueIsDouble() {
		Constant e = Constant.parse("123d");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Double.class)));
	}

	@Test
	public void parseConstantExpression_123FFloatValue_TheValueIsFloat() {
		Constant e = Constant.parse("123F");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Float.class)));
	}

	@Test
	public void parseConstantExpression_123DDoubleValue_TheValueIsDouble() {
		Constant e = Constant.parse("123D");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Double.class)));
	}

	@Test
	public void parseConstantExpression_123D321FloatValue_TheValueIs123D321() {
		Constant e = Constant.parse("123.321");
		Object constant = e.getValue();
		assertThat(constant, is(equalTo(123.321)));
	}

	@Test
	public void parseConstantExpression_123D321FloatValue_TheValueIsFloat() {
		Constant e = Constant.parse("123.321");
		Object constant = e.getValue();
		assertThat(constant.getClass(), is(assignableTo(Double.class)));
	}

	@Test(expected = IllegalOperandException.class)
	public void createConstantExpression_WithContextValue_IllegalOperandExceptionIsThrown() {
		constant(context());
	}

	@Test(expected = IllegalOperandException.class)
	public void createConstantExpression_WithInstanceExpressionAsValue_IllegalOperandExceptionIsThrown() {
		constant(instance());
	}

	@Test
	public void evaluateConstantExpression_WithObjectValue_ReturnsTheObject()
			throws Exception {
		Object value = new Object();
		Expression e = constant(value);
		assertThat(e.evaluate(), is(value));
	}
}
