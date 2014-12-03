package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.coalesce;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Coalesce;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Rat;
import org.crossv.tests.subjects.WhiteMouse;
import org.junit.Test;

public class CoalesceExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createCoalesceExpression_PrimitiveAndReferenceOperands_ThrowsIllegalOperandException() {
		coalesce(1, "1");
	}

	@Test(expected = IllegalOperandException.class)
	public void createCoalesceExpression_ReferenceAndPrimitiveOperands_ThrowsIllegalOperandException() {
		coalesce("1", 1);
	}

	@Test(expected = IllegalOperandException.class)
	public void createCoalesceExpression_ObjectNotInheritedOneFronAnotherOperands_ThrowsIllegalOperandException() {
		coalesce(new Rat(), new WhiteMouse());
	}

	@Test
	public void createCoalesceExpression_StringAndObjectOperands_ReturnClassIsObject() {
		Class<?> expectedClass = Object.class;
		Object left = "1";
		Object right = new Object();
		Expression e = coalesce(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createCoalesceExpression_NullAndObjectOperands_ReturnClassIsString() {
		Class<?> expectedClass = String.class;
		Object left = null;
		Object right = new String();
		Expression e = coalesce(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createCoalesceExpression_ObjectAndStringOperands_ReturnClassIsString() {
		Class<?> expectedClass = Object.class;
		Object left = new Object();
		Object right = "1";
		Expression e = coalesce(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createCoalesceExpression_callingToString_getsJavaLikeExpression() {
		Expression e = coalesce("a", "b");
		assertThat(e.toString(), is("\"a\" ?? \"b\""));
	}

	@Test
	public void parseCoalesceExpression_AAndB_LeftConstantIsA() {
		Coalesce e = Coalesce.parse("\"a\" ?? \"b\"");
		Constant constant = (Constant) e.getLeft();
		assertThat(constant.getValue(), is(equalTo("a")));
	}

	@Test
	public void parseCoalesceExpression_AAndB_RightConstantIsB() {
		Coalesce e = Coalesce.parse("\"a\" ?? \"b\"");
		Constant constant = (Constant) e.getRight();
		assertThat(constant.getValue(), is(equalTo("b")));
	}

	@Test
	public void evaluatingAParsedCoalesceExpression_AAndB_RetunsA()
			throws Exception {
		Expression e = Coalesce.parse("\"a\" ?? \"b\"");
		assertThat(e.evaluate(), is(equalTo("a")));
	}


	@Test
	public void evaluatingAParsedCoalesceExpression_NullAndAStringWithQuotationMarks_RetunsQuotationMarks()
			throws Exception {
		Expression e = Coalesce.parse("null ?? \"\\\"\\\"\\\"\\\"\"");
		assertThat(e.evaluate(), is(equalTo("\\\"\\\"\\\"\\\"")));
	}

	@Test
	public void evaluateCoalesceExpression_NotNullOnLeft_ReturnsLeft()
			throws Exception {
		Expression e = coalesce(constant("1"), constant("2"));
		assertThat(e.evaluate(), is(equalTo("1")));
	}

	@Test
	public void evaluateCoalesceExpression_NullOnLeft_ReturnsRight()
			throws Exception {
		Expression e = coalesce(constant(null), constant("2"));
		assertThat(e.evaluate(), is(equalTo("2")));
	}
}
