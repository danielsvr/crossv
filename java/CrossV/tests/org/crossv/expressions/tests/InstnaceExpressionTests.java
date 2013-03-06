package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.instance;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class InstnaceExpressionTests {

	@Test
	public void evaluateContextExpression_WithObjectValue_ReturnsTheObject() {
		Object evaluatedIntance = new Monkey();
		Expression e = instance();
		Object evaluation = e.evaluate(evaluatedIntance);
		assertThat(evaluation, is(evaluatedIntance));
	}
}
