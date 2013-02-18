package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
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
		Expression e = constant((byte)-1);
		assertThat(e.toString(), is("(-1)"));
	}
	
	@Test
	public void createConstantWithPositiveByteValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((byte)1);
		assertThat(e.toString(), is("1"));
	}

	@Test
	public void createConstantWithNegativeShortValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((short)-1);
		assertThat(e.toString(), is("(-1)"));
	}
	
	@Test
	public void createConstantWithPositiveShortValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((short)1);
		assertThat(e.toString(), is("1"));
	}

	@Test
	public void createConstantWithNegativeIntValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((int)-1);
		assertThat(e.toString(), is("(-1)"));
	}
	
	@Test
	public void createConstantWithPositiveIntValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((int)1);
		assertThat(e.toString(), is("1"));
	}

	@Test
	public void createConstantWithNegativeLongValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((long)-1);
		assertThat(e.toString(), is("(-1)"));
	}
	
	@Test
	public void createConstantWithPositiveLongValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((long)1);
		assertThat(e.toString(), is("1"));
	}

	@Test
	public void createConstantWithNegativeFloatValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((float)-1);
		assertThat(e.toString(), is("(-1.0)"));
	}
	
	@Test
	public void createConstantWithPositiveFloatValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((float)1);
		assertThat(e.toString(), is("1.0"));
	}

	@Test
	public void createConstantWithNegativeDoubleValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((double)-1);
		assertThat(e.toString(), is("(-1.0)"));
	}
	
	@Test
	public void createConstantWithPositiveDoubleValueExpression_callingToString_getsJavaLikeExpression() {
		Expression e = constant((double)1);
		assertThat(e.toString(), is("1.0"));
	}
}
