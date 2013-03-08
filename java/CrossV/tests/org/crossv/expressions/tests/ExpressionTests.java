package org.crossv.expressions.tests;

import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpressionTests {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void evaluateConditionalExpression_TestIsTrue_ReturnsIfTrueValue()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Unrecognized expression");

		Expression unknown = new Expression() {
			@Override
			public Class<?> getResultClass() {
				return null;
			}
		};

		unknown.evaluate();
	}
}
