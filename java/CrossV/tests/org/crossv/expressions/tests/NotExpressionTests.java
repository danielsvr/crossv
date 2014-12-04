package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.not;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.Not;
import org.junit.Test;

public class NotExpressionTests {

	@Test
	public void createNotExpressionForTrueValue_callingToString_getsJavaLikeExpression() {
		Expression e = not(true);
		assertThat(e.toString(), is("!true"));
	}

	@Test
	public void parseNotExpression_TrueValue_OperandIsTrue() {
		Not e = Not.parse("!true");
		Constant constant = (Constant) e.getOperand();
		assertThat(constant.getValue(), is(equalTo(true)));
	}

	@Test
	public void evaluateParsedNotExpression_TrueValue_ReturnsFalse()
			throws Exception {
		Expression e = Not.parse("!true");
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test(expected = ClassCastException.class)
	public void evaluateParsedNotdExpression_2TimesTrue_ThrowsCastException()
			throws Exception {
		Not.parse("!!true");
	}

	@Test(expected = IllegalOperandException.class)
	public void createNotExpressionForNonBooleanValue_ThrowsIllegalOperandException() {
		not(constant(123));
	}

	@Test
	public void createNotExpression_BooleanOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		boolean operand = true;
		Expression e = not(operand);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void evaluateNotExpression_TrueOperand_ReturnsFalse()
			throws Exception {
		boolean operand = true;
		Expression e = not(operand);
		assertThat(e.evaluate(), is(equalTo(false)));
	}

	@Test
	public void evaluateNotExpression_FalseOperand_ReturnsTrue()
			throws Exception {
		boolean operand = false;
		Expression e = not(operand);
		assertThat(e.evaluate(), is(equalTo(true)));
	}
}
