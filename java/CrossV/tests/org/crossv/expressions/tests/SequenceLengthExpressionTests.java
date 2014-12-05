package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.sequenceLength;
import static org.crossv.primitives.Iterables.repeat;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Enumeration;
import java.util.StringTokenizer;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.Instance;
import org.crossv.expressions.SequenceLength;
import org.junit.Test;

public class SequenceLengthExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createSequenceLength_WithNullInstance_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceLength((Expression) null);
	}

	@Test(expected = IllegalOperandException.class)
	public void createSequenceLength_WithNonIterableInstance_ThrowsIllegalArgumentException()
			throws Exception {
		sequenceLength(constant(123));
	}

	@Test
	public void createSequenceLength_WithAnyInstance_ReturnClassIsInteger()
			throws Exception {
		Expression e = sequenceLength(new int[0]);
		Class<?> resultClass = e.getResultClass();
		assertThat(resultClass, is(equalTo(Integer.class)));
	}

	@Test
	public void evaluateSequenceLength_With3ElementsArray_Returns3()
			throws Exception {
		int[] instance = new int[] { 1, 2, 3 };
		Expression e = sequenceLength(instance);

		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void evaluateSequenceLength_With3CharsString_Returns3()
			throws Exception {
		String instance = "123";
		Expression e = sequenceLength(instance);

		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void evaluateSequenceLength_With3ElementsEnumeration_Returns3()
			throws Exception {
		Enumeration<Object> instance = new StringTokenizer("1,2,3", ",");
		Expression e = sequenceLength(instance);

		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void evaluateSequenceLength_With3ElementsIterable_Returns3()
			throws Exception {
		Iterable<String> instance = repeat("1", 3);
		Expression e = sequenceLength(instance);

		assertThat(e.evaluate(), is(equalTo(3)));
	}

	@Test
	public void parseSequenceLengthExpression_OnInstance_OperandIsInstance()
			throws Exception {
		SequenceLength sqLen = SequenceLength.parse("obj.length");
		Expression sq = sqLen.getOperand();
		assertThat(sq.getClass(), is(assignableTo(Instance.class)));
	}

	@Test
	public void evaluateParsedSequenceLengthExpression_On3ElementArrayInstance_Returns3()
			throws Exception {
		Expression sqLen = SequenceLength.parse("obj.length");
		Object value = sqLen.evaluate(new int[] { 1, 2, 3 });
		assertThat(value, is(equalTo(3)));
	}

	@Test
	public void evaluateParsedSequenceLengthExpression_On3ElementArray_Returns3()
			throws Exception {
		Expression sqLen = SequenceLength
				.parse("new java.lang.Integer[] { 1, 1, 1 }.length");
		Object value = sqLen.evaluate();
		assertThat(value, is(equalTo(3)));
	}

	@Test
	public void sequenceLengthToString_With3ElementsIterable_ReturnsArrayLikeExpression()
			throws Exception {
		Iterable<Integer> instance = repeat(1, 3);
		Expression e = sequenceLength(instance);

		String stringExpression = "new java.lang.Integer[] { 1, 1, 1 }.length";
		assertThat(e.toString(), is(stringExpression));
	}

	@Test
	public void sequenceLengthToString_With3CharString_ReturnsStringLikeExpression()
			throws Exception {
		String instance = "123";
		Expression e = sequenceLength(instance);

		assertThat(e.toString(), is("\"123\".length"));
	}

	@Test
	public void sequenceLengthToString_With3ElementArray_ReturnsArrayLikeExpression()
			throws Exception {
		int[] instance = new int[] { 1, 2, 3 };
		Expression e = sequenceLength(instance);

		String stringExpression = "new java.lang.Integer[] { 1, 2, 3 }.length";
		assertThat(e.toString(), is(stringExpression));
	}

	@Test
	public void sequenceLengthToString_With3ElementEnumeration_ReturnsArrayLikeExpression()
			throws Exception {
		Enumeration<Object> instance = new StringTokenizer("1,2,3", ",");
		Expression e = sequenceLength(instance);

		String stringExpression = "new java.lang.String[] { \"1\", \"2\", \"3\" }.length";
		assertThat(e.toString(), is(stringExpression));
	}
}
