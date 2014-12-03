package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.context;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Context;
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

	@Test
	public void contextExpression_ToString_ReturnsJavaLikeExpression()
			throws Exception {
		Expression e = context();
		assertThat(e.toString(), is("context"));
	}

	@Test
	public void parsContextExpression_Context_ReturnsContext() throws Exception {
		Context context = Context.parse("context");
		assertThat(context, is(not(nullValue())));
	}

	@Test(expected = EvaluationException.class)
	public void evaluateContextExpression_WithoutProvidingContextValue_ThrowsEvaluationException()
			throws Exception {
		Expression e = context();
		e.evaluate(null);
	}
}
