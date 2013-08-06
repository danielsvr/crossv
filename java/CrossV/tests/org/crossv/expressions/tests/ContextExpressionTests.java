package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.context;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.tests.helpers.TestObjectFactory;
import org.junit.Test;

public class ContextExpressionTests {

	@Test
	public void evaluateContextExpression_ProvidingAMonkeyContextValue_ReturnsTheMonkey()
			throws Exception {
		Object contextIntance = TestObjectFactory.createMonkey();
		Expression e = context();
		Object evaluation = e.evaluate(null, contextIntance);
		assertThat(evaluation, is(contextIntance));
	}

	@Test(expected = EvaluationException.class)
	public void evaluateContextExpression_WithoutProvidingContextValue_ThrowsEvaluationException()
			throws Exception {
		Expression e = context();
		e.evaluate(null);
	}
}
