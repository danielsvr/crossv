package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.complemented;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Complement;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.Negate;
import org.junit.Test;

public class ComplementExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createComplementedStringExpression_ThrowsIllegalOperandException() {
		complemented("string");
	}

	@Test(expected = IllegalOperandException.class)
	public void createComplementedDoubleExpression_ThrowsIllegalOperandException() {
		complemented((double) 1);
	}

	@Test
	public void createComplementExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = complemented((int) -1);
		assertThat(e.toString(), is("~(-1)"));
	}

	@Test
	public void parseComplementExpression_Minus1_OperandIsNegate() {
		Complement e = Complement.parse("~(-1)");
		Expression operand = e.getOperand();
		assertThat(operand.getClass(), is(assignableTo(Negate.class)));
	}

	@Test
	public void parseComplementExpression_Minus1_OperandIsNegated1() {
		Complement e = Complement.parse("~(-1)");
		Negate negation = (Negate) e.getOperand();
		Constant constant = (Constant) negation.getOperand();
		assertThat(constant.getValue(), is(equalTo(1)));
	}

	@Test
	public void evaluatingAParsedComplementExpression_Minus1_Retuns0()
			throws Exception {
		Expression e = Complement.parse("~(-1)");
		assertThat(e.evaluate(), is(equalTo(0)));
	}

	@Test(expected = ClassCastException.class)
	public void evaluatingAParsedComplementExpression_2Times2_ThrowsCastException()
			throws Exception {
		Complement.parse("~~2");
	}

	@Test
	public void evaluatingAParsedComplementExpression_3Times2_RetunsMinus3()
			throws Exception {
		Expression e = Complement.parse("~~~2");
		assertThat(e.evaluate(), is(equalTo(~~~2/*-3*/)));
	}

	@Test
	public void createNegatedDoubleContextValue_callingToString_getsJavaLikeExpression() {
		Expression e = complemented(context(Long.class));
		assertThat(e.toString(), is("~context"));
	}

	@Test
	public void evaluateNegatedExpression_NegativeValue_ReturnsPositiveValue()
			throws Exception {
		int value = -1;
		Expression e = complemented(value);
		assertThat(e.evaluate(), is(equalTo(~(-1))));
	}

	@Test
	public void evaluateNegatedExpression_PositoveLongValue_ReturnsNegativeLongValue()
			throws Exception {
		long value = 1;
		Expression e = complemented(value);
		assertThat(e.evaluate(), is(equalTo(~1L)));
	}
}
