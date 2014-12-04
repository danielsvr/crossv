package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.notEqual;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.NotEqual;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.Rat;
import org.crossv.tests.subjects.WhiteMouse;
import org.junit.Test;

public class NotEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createNotEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		notEqual(new WhiteMouse(), new Rat());
	}

	@Test
	public void createEqualExpression_SuperAndSubClassOperands_IllegalOperandExceptionIsNotThrown() {
		notEqual(new Mouse(), new Rat());
	}

	@Test
	public void createEqualExpression_SubAndSuperClassOperands_IllegalOperandExceptionIsNotThrown() {
		notEqual(new Rat(), new Mouse());
	}

	@Test
	public void createNotEqualExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		Object left = 1;
		Object right = 2;
		Expression e = notEqual(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createNotEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = notEqual(1, 2);
		assertThat(e.toString(), is("1 != 2"));
	}

	@Test
	public void parseNotEqualExpression_1And2_LeftConstantIs1() {
		NotEqual e = NotEqual.parse("1 != 2");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void parseNotEqualExpression_1And2_RightConstantIs2() {
		NotEqual e = NotEqual.parse("1 != 2");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo(2)));
	}

	@Test
	public void evaluatingAParsedNotEqualExpression_1And2_RetunsTrue()
			throws Exception {
		Expression e = NotEqual.parse("1 != 2");
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateNotEqualExpression_SameValueOnLoftAndRight_ReturnsFalse()
			throws Exception {
		Expression e = notEqual(1, 1);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateNotEqualExpression_DifferentValueOnLoftAndRight_ReturnsTrue()
			throws Exception {
		Expression e = notEqual(2, 1);
		assertThat(e.evaluate(), is(equalTo(true)));
	}

	@Test
	public void evaluateNotEqualExpression_NullOnLeftAndIntOnRight_ReturnsFalse()
			throws Exception {
		Expression e = notEqual(constant(null), 1);
		assertThat(e.evaluate(), is(equalTo(true)));
	}
}
