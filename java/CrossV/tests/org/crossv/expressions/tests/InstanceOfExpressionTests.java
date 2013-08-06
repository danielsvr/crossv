package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.instance;
import static org.crossv.expressions.Expression.instanceOf;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.Rat;
import org.junit.Test;

public class InstanceOfExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createInstanceOfExpression_NotAReferenceOnLeftOperand_IllegalOperandExceptionIsThrown() {
		int left = 1;
		Class<?> right = String.class;
		instanceOf(left, right);
	}

	@Test(expected = IllegalOperandException.class)
	public void createInstanceOfExpression_NotAClassOnRight_IllegalOperandExceptionIsThrown() {
		String left = "1";
		Expression right = constant(2);
		instanceOf(left, right);
	}

	@Test
	public void createInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		String left = "1";
		Class<?> right = Integer.class;
		Expression e = instanceOf(left, right);
		assertThat(e.toString(), is("\"1\" instanceof java.lang.Integer"));
	}

	@Test
	public void createInstanceOfExpression_StringValueAndIntegerClassOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		String left = "1";
		Class<?> right = Integer.class;
		Expression e = instanceOf(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createContextInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression left = context();
		Class<String> right = String.class;
		Expression e = instanceOf(left, right);
		assertThat(e.toString(), is("context instanceof java.lang.String"));
	}

	@Test
	public void createInstanceInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression left = instance();
		Class<String> right = String.class;
		Expression e = instanceOf(left, right);
		assertThat(e.toString(), is("obj instanceof java.lang.String"));
	}

	@Test
	public void evaluateInstanceOfExpression_StringValueInstanceOfString_ReturnsTrue()
			throws Exception {
		Expression left = constant("value");
		Class<String> right = String.class;
		Expression e = instanceOf(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateInstanceOfExpression_MonkeyValueInstanceOfString_ReturnsFalse()
			throws Exception {
		Monkey monkey = TestObjectFactory.createMonkey();
		
		Expression left = constant(monkey);
		Class<String> right = String.class;
		Expression e = instanceOf(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateInstanceOfExpression_NullValueInstanceOfString_ReturnsFalse()
			throws Exception {
		Expression left = constant(null);
		Class<String> right = String.class;
		Expression e = instanceOf(left, right);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateInstanceOfExpression_RatValueInstanceOfMouse_ReturnsTrue()
			throws Exception {
		Expression left = constant(new Rat());
		Class<Mouse> right = Mouse.class;
		Expression e = instanceOf(left, right);
		assertThat(e.evaluate(), is(equalTo(true)));
	}
}
