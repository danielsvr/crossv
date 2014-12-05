package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.sequenceIndex;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.primitives.Iterables.repeat;
import static org.crossv.tests.helpers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Enumeration;
import java.util.StringTokenizer;

import org.crossv.expressions.Constant;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.Instance;
import org.crossv.expressions.SequenceIndex;
import org.junit.Test;

public class SequenceIndexExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createSequenceIndex_WithNullInstance_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceIndex((Expression) null, (Expression) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createSequenceIndex_WithNullIndex_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceIndex(constant(123), (Expression) null);
	}

	@Test(expected = IllegalOperandException.class)
	public void createSequenceIndex_WithNonIterableInstance_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceIndex(constant(123), constant(0));
	}

	@Test(expected = IllegalOperandException.class)
	public void createSequenceIndex_WithNonIntegerIndex_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceIndex(constant(new int[0]), constant("index"));
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
	public void evaluateSequenceExpressionIndex_With42Repeated3TimesAsIterable_Returns42()
			throws Exception {
		Iterable<Integer> instance = repeat(42, 3);
		Expression e = sequenceIndex(instance, 1);

		assertThat(e.evaluate(), is(equalTo(42)));
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
	public void sequenceIndex1ToString_OnInstance_ReturnsArrayIndexLikeExpression()
			throws Exception {
		Expression instance = instance();
		Expression e = sequenceIndex(instance, 1);

		assertThat(e.toString(), is("obj[1]"));
	}

	@Test
	public void parseSequenceIndexExpression_Index1OnInstance_SequenceIsInstance()
			throws Exception {
		SequenceIndex sqIdx = SequenceIndex.parse("obj[1]");
		Expression sq = sqIdx.getSequence();
		assertThat(sq.getClass(), is(assignableTo(Instance.class)));
	}

	@Test
	public void parseSequenceIndexExpression_Index1OnInstance_IndexConstantIs1()
			throws Exception {
		SequenceIndex sqIdx = SequenceIndex.parse("obj[1]");
		Constant idx = (Constant) sqIdx.getIndex();
		assertThat(idx.getValue(), is(equalTo(1)));
	}

	@Test
	public void evaluateParsedSequenceIndexExpression_Index1On3ElementArrayInstance_ReturnsElementArIdex1()
			throws Exception {
		Expression sqIdx = SequenceIndex.parse("obj[1]");
		Object value = sqIdx.evaluate(new int[] { 1, 2, 3 });
		assertThat(value, is(equalTo(2)));
	}

	@Test
	public void evaluateParsedSequenceIndexExpression_Index1On3ElementArray_ReturnsElementArIdex1()
			throws Exception {
		Expression sqIdx = SequenceIndex
				.parse("new java.lang.String[] { \"1\", \"@ one\", \"1\" }[1]");
		Object value = sqIdx.evaluate();
		assertThat(value, is(equalTo("@ one")));
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
