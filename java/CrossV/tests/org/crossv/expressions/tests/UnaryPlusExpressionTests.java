package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.plus;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.junit.Test;

public class UnaryPlusExpressionTests {

	@Test
	public void createNegatedByteExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((byte)1);
		assertThat(e.toString(), is("+1"));
	}
	
	@Test
	public void createNegatedByteExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((byte)-1);
		assertThat(e.toString(), is("+(-1)"));
	}
	
	@Test
	public void createNegatedByteContextForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Byte.class));
		assertThat(e.toString(), is("+context"));
	}

	@Test
	public void createNegatedShortExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((short)1);
		assertThat(e.toString(), is("+1"));
	}
	
	@Test
	public void createNegatedShortExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((short)-1);
		assertThat(e.toString(), is("+(-1)"));
	}
	
	@Test
	public void createNegatedShortContextForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Short.class));
		assertThat(e.toString(), is("+context"));
	}
	
	@Test
	public void createNegatedIntExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((int)1);
		assertThat(e.toString(), is("+1"));
	}
	
	@Test
	public void createNegatedIntExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((int)-1);
		assertThat(e.toString(), is("+(-1)"));
	}	

	@Test
	public void createNegatedIntegerContextForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Integer.class));
		assertThat(e.toString(), is("+context"));
	}
	
	@Test
	public void createNegatedLongExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((long)1);
		assertThat(e.toString(), is("+1"));
	}
	
	@Test
	public void createNegatedLongExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((long)-1);
		assertThat(e.toString(), is("+(-1)"));
	}
	
	@Test
	public void createNegatedLongContextForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Long.class));
		assertThat(e.toString(), is("+context"));
	}
	
	@Test
	public void createNegatedFloatExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((float)1);
		assertThat(e.toString(), is("+1.0"));
	}
	
	@Test
	public void createNegatedFloatExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((float)-1);
		assertThat(e.toString(), is("+(-1.0)"));
	}
	
	@Test
	public void createNegatedFloatContextForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Float.class));
		assertThat(e.toString(), is("+context"));
	}
	
	@Test
	public void createNegatedDoubleExpressionForPositiveValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((double)1);
		assertThat(e.toString(), is("+1.0"));
	}
	
	@Test
	public void createNegatedDoubleExpressionForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus((double)-1);
		assertThat(e.toString(), is("+(-1.0)"));
	}
	
	@Test
	public void createNegatedDoubleContextForNegativeValue_callingToString_getsJavaLikeExpression() {
		Expression e = plus(context(Double.class));
		assertThat(e.toString(), is("+context"));
	}
}
