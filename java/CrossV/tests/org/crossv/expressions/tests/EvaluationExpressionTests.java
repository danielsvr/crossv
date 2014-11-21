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
				is("when obj instanceof org.crossv.tests.subjects.Monkey ["
						+ "obj.nickname validif !(obj.nickname == null) else \"nickname should not be null\""
						+ "]"));
	}
}
