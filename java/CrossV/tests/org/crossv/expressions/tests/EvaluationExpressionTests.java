package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.equal;
import static org.crossv.expressions.Expression.evaluation;
import static org.crossv.expressions.Expression.instance;
import static org.crossv.expressions.Expression.instanceOf;
import static org.crossv.expressions.Expression.memberAccess;
import static org.crossv.expressions.Expression.not;
import static org.crossv.expressions.Expression.validIf;
import static org.crossv.primitives.Iterables.firstOrDefault;
import static org.crossv.primitives.Iterables.toIterable;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.Evaluator;
import org.crossv.NoContext;
import org.crossv.expressions.Expression;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class EvaluationExpressionTests {

	@Test
	public void monkey() throws Exception {
		Expression scope = instanceOf(instance(), Monkey.class);
		Expression validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Iterable<Expression> evaluators = toIterable(validNickname);
		Expression e = evaluation(scope, evaluators);
		Evaluator evaluator = (Evaluator) e.evaluate();
		Iterable<Evaluation> result = evaluator.evaluate(new Monkey(),
				NoContext.instance);
		Evaluation first = firstOrDefault(result);
		assertThat(first.getMessage(), is("nickname should not be null"));
	}

	@Test
	public void monkey1() throws Exception {
		Expression scope = instanceOf(instance(), Monkey.class);
		Expression validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Iterable<Expression> evaluators = toIterable(validNickname);
		Expression e = evaluation(scope, evaluators);
		Evaluator evaluator = (Evaluator) e.evaluate();
		Iterable<Evaluation> result = evaluator.evaluate(new Monkey(),
				NoContext.instance);
		Evaluation first = firstOrDefault(result);
		assertThat(first, is(assignableTo(EvaluationFault.class)));
	}

	@Test
	public void monkey2() {
		Expression scope = instanceOf(instance(), Monkey.class);
		Expression validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Iterable<Expression> evaluators = toIterable(validNickname);
		Expression e = evaluation(scope, evaluators);
		assertThat(
				e.toString(),
				is("when obj instanceof Monkey [obj.nickname validif !(obj.nickname == null) else \"nickname should not be null\"]"));
	}

	//
	// @Test
	// public void
	// createAddExpression_StringAndObjectOperands_ReturnClassIsString() {
	// Class<?> expectedClass = String.class;
	// Object left = "1";
	// Object right = new Object();
	// Expression e = add(left, right);
	// assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	// }
	//
	// @Test
	// public void
	// createAddExpression_ObjectAndStringOperands_ReturnClassIsString() {
	// Class<?> expectedClass = String.class;
	// Object left = new Object();
	// Object right = "1";
	// Expression e = add(left, right);
	// assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	// }
	//
	// @Test
	// public void createAddExpression_NumberOperands_ReturnClassIsNumber() {
	// Class<?> expectedClass = Number.class;
	// Object left = (int) 1;
	// Object right = (byte) 1;
	// Expression e = add(left, right);
	// assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	// }
	//
	// @Test
	// public void createAddExpression_callingToString_getsJavaLikeExpression()
	// {
	// Expression e = add(1, 2);
	// assertThat(e.toString(), is("1 + 2"));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_TwoIntegerValues_ReturnsAddedValuesAsInteger()
	// throws Exception {
	// int left = 1;
	// int right = 2;
	// int expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_IntAndLongValues_ReturnsAddedValuesAsLong()
	// throws Exception {
	// int left = 1;
	// long right = 2;
	// long expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_FloatAndIntValues_ReturnsAddedValuesAsFloat()
	// throws Exception {
	// float left = 1;
	// int right = 2;
	// float expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_LongAndDoubleValues_ReturnsAddedValuesAsDouble()
	// throws Exception {
	// long left = 1;
	// double right = 2;
	// double expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_StringAndObjectValues_ReturnsConcatenatedStrings()
	// throws Exception {
	// String left = "my ";
	// Object right = new ObjectToString("object");
	// String expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_StringAndNullValues_ReturnsConcatenatedStrings()
	// throws Exception {
	// String left = "my ";
	// Object right = null;
	// String expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
	//
	// @Test
	// public void
	// evaluateAddExpression_NullAndStringValues_ReturnsConcatenatedStrings()
	// throws Exception {
	// Object left = null;
	// String right = " my";
	// String expected = left + right;
	//
	// Expression e = add(left, right);
	// assertThat(e.evaluate(), is(equalTo(expected)));
	// }
}
