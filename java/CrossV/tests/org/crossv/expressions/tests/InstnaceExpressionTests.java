package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.instance;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class InstnaceExpressionTests {

	@Test
	public void evaluateInstanceExpression_ProvidingAMonkeyInstanceValue_ReturnsTheMonkey()
			throws Exception {
		Object evaluatedIntance = TestObjectFactory.createMonkey();
		Expression e = instance();
		Object evaluation = e.evaluate(evaluatedIntance);
		assertThat(evaluation, is(evaluatedIntance));
	}

	@Test(expected = EvaluationException.class)
	public void evaluateInstanceExpression_WithoutProvidingAnInstanceValue_EvaluationExceptionIsThrown()
			throws Exception {
		Expression e = instance();
		e.evaluate();
	}
}
