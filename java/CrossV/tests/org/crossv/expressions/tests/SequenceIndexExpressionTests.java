package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.sequenceIndex;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.primitives.Iterables.repeat;
import static org.crossv.tests.helpers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Enumeration;
import java.util.StringTokenizer;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class SequenceIndexExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createSequenceIndex_WithNullInstance_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceIndex((Expression) null, (Expression) null);
	}

	@Test(expected = IllegalOperandException.class)
	public void createSequenceIndex_WithNonIterableInstance_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceIndex(constant(123), constant(0));
	}

	@Test
	public void evaluateSequenceIndex1_With123ElementsArray_Returns2()
			throws Exception {
		int[] instance = new int[] { 1, 2, 3 };
		Expression e = sequenceIndex(instance, 1);

		assertThat(e.evaluate(), is(equalTo(2)));
	}

	@Test
	public void evaluateSequenceExpressionIndex_With123ElementsArray_Returns3()
			throws Exception {
		int[] instance = new int[] { 1, 2, 3 };
		Expression e = sequenceIndex(constant(instance),
				sequenceIndex(instance, 1));

		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void evaluateSequenceIndex1_With123CharsString_ReturnsChar2()
			throws Exception {
		String instance = "123";
		Expression e = sequenceIndex(instance, 1);

		assertThat(e.evaluate(), is(equalTo('2')));
	}

	@Test
	public void evaluateSequenceIndex1_With123StringElementsEnumeration_ReturnsString2()
			throws Exception {
		Enumeration<Object> instance = new StringTokenizer("1,2,3", ",");
		Expression e = sequenceIndex(instance, 1);

		assertThat(e.evaluate(), is(equalTo("2")));
	}

	@Test
	public void sequenceIndex1ToString_With3ElementsIterable_ReturnsArrayLikeExpression()
			throws Exception {
		Iterable<Integer> instance = repeat(1, 3);
		Expression e = sequenceIndex(instance, 1);

		String stringExpression = "new java.lang.Integer[] { 1, 1, 1 }[1]";
		assertThat(e.toString(), is(stringExpression));
	}

	@Test
	public void sequenceIndex1ToString_With3CharString_ReturnsStringLikeExpression()
			throws Exception {
		String instance = "123";
		Expression e = sequenceIndex(instance, 1);

		assertThat(e.toString(), is("\"123\"[1]"));
	}

	@Test
	public void sequenceIndex1ToString_With3ElementArray_ReturnsArrayLikeExpression()
			throws Exception {
		int[] instance = new int[] { 1, 2, 3 };
		Expression e = sequenceIndex(instance, 1);

		String stringExpression = "new java.lang.Integer[] { 1, 2, 3 }[1]";
		assertThat(e.toString(), is(stringExpression));
	}

	@Test
	public void sequenceIndex1ToString_With3ElementEnumeration_ReturnsArrayLikeExpression()
			throws Exception {
		Enumeration<Object> instance = new StringTokenizer("1,2,3", ",");
		Expression e = sequenceIndex(instance, 1);

		String stringExpression = "new java.lang.String[] { \"1\", \"2\", \"3\" }[1]";
		assertThat(e.toString(), is(stringExpression));
	}

	@Test
	public void sequenceIndexWithExpressionToString_With3ElementArray_ReturnsArrayLikeExpression()
			throws Exception {
		int[] instance = new int[] { 1, 2, 3 };
		Expression e = sequenceIndex(constant(instance),
				sequenceIndex(constant(instance), 0));

		String stringExpression = "new java.lang.Integer[] { 1, 2, 3 }[new java.lang.Integer[] { 1, 2, 3 }[0]]";
		assertThat(e.toString(), is(stringExpression));
	}
}
