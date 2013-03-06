package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.context;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ContextExpressionTests {

	@Test
	public void evaluateContextExpression_WithObjectValue_ReturnsTheObject() {
		Object contextIntance = new Monkey();
		Expression e = context();
		Object evaluation = e.evaluate(null, contextIntance);
		assertThat(evaluation, is(contextIntance));
	}
}
