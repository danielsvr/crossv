package org.crossv.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAndExpression_nonBooleanOperands_IllegalOperandExceptionIsThrown() {
		and(constant(1), constant(2));
	}

	@Test
	public void createAndExpression_callingToString_getsJavaLikeExpression() {
		Expression e = and(true, false);
		assertThat(e.toString(), is("(true && false)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		equal(1, "2");
	}

	@Test
	public void createEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = equal(1, 2);
		assertThat(e.toString(), is("(1 == 2)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		greaterThan(1, "2");
	}

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		greaterThan(new Object(), new Object());
	}

	@Test
	public void createGraterThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThan(1, 2);
		assertThat(e.toString(), is("(1 > 2)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanOrEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		greaterThanOrEqual(1, "2");
	}

	@Test(expected = IllegalOperandException.class)
	public void createGraterThanOrEqualExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		greaterThanOrEqual(new Object(), new Object());
	}

	@Test
	public void createGraterThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = greaterThanOrEqual(1, 2);
		assertThat(e.toString(), is("(1 >= 2)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createInstanceOfExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		instanceOf(1, constant(2));
	}

	@Test
	public void createInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression e = instanceOf("1", Integer.class);
		assertThat(e.toString(), is("(\"1\" instanceof java.lang.Integer)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createLessThanExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		lessThan(1, "2");
	}

	@Test(expected = IllegalOperandException.class)
	public void createLessThanExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		lessThan(new Object(), new Object());
	}

	@Test
	public void createLessThanExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThan(1, 2);
		assertThat(e.toString(), is("(1 < 2)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createLessThanOrEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		lessThanOrEqual(1, "2");
	}

	@Test(expected = IllegalOperandException.class)
	public void createLessThanOrEqualExpression_TowReferecesOperands_IllegalOperandExceptionIsThrown() {
		lessThanOrEqual(new Object(), new Object());
	}

	@Test
	public void createLessThanOrEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = lessThanOrEqual(1, 2);
		assertThat(e.toString(), is("(1 <= 2)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createNotEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		notEqual(1, "2");
	}

	@Test
	public void createNotEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = notEqual(1, 2);
		assertThat(e.toString(), is("(1 != 2)"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createOrExpression_nonBooleanOperands_IllegalOperandExceptionIsThrown() {
		or(constant(1), constant(2));
	}

	@Test
	public void createOrExpression_callingToString_getsJavaLikeExpression() {
		Expression e = or(true, false);
		assertThat(e.toString(), is("(true || false)"));
	}

	@Test
	public void createContextInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression e = instanceOf(context(), String.class);
		assertThat(e.toString(), is("(context instanceof java.lang.String)"));
	}

	@Test
	public void createInstanceInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression e = instanceOf(instance(), String.class);
		assertThat(e.toString(), is("(obj instanceof java.lang.String)"));
	}

	@Test
	public void createConstantWithNullValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant(null);
		assertThat(e.toString(), is("null"));
	}

	@Test
	public void createCallHashCodeExpressionForString_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call("123", "hashCode");
		assertThat(e.toString(), is("\"123\".hashCode()"));
	}

	@Test
	public void createCallEqualsExpressionForString_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = call("123", "equals", 321);
		assertThat(e.toString(), is("\"123\".equals(321)"));
	}

	@Test
	public void createCallGetNameExpressionForMonkeyContext_callingToString_getsJavaLikeExpression() throws Exception  {
		Expression e = equal(call(context(Monkey.class), "getName"), "name");
		assertThat(e.toString(), is("(context.getName() == \"name\")"));
	}

	@Test
	public void createNegatedByteExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((byte)1);
		assertThat(e.toString(), is("-1"));
	}
	
	@Test
	public void createNegatedByteExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((byte)-1);
		assertThat(e.toString(), is("-(-1)"));
	}

	@Test
	public void createNegatedShortExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((short)1);
		assertThat(e.toString(), is("-1"));
	}
	
	@Test
	public void createNegatedShortExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((short)-1);
		assertThat(e.toString(), is("-(-1)"));
	}
	
	@Test
	public void createNegatedIntExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((int)1);
		assertThat(e.toString(), is("-1"));
	}
	
	@Test
	public void createNegatedIntExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = negate((int)-1);
		assertThat(e.toString(), is("-(-1)"));
	}
}
