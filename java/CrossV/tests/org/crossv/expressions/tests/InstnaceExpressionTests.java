package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.instance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.expressions.Instance;
import org.crossv.tests.helpers.TestObjectFactory;
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

	@Test
	public void contextExpression_ToString_ReturnsJavaLikeExpression()
			throws Exception {
		Expression e = instance();
		assertThat(e.toString(), is("obj"));
	}

	@Test
	public void parsContextExpression_Context_ReturnsContext() throws Exception {
		Instance insntace = Instance.parse("obj");
		assertThat(insntace, is(not(nullValue())));
	}

	@Test(expected = EvaluationException.class)
	public void evaluateInstanceExpression_WithoutProvidingAnInstanceValue_EvaluationExceptionIsThrown()
			throws Exception {
		Expression e = instance();
		e.evaluate();
	}
}
