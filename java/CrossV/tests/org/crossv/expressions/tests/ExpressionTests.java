package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.add;
import static org.crossv.expressions.ExpressionEvaluator.NO_CONTEXT;
import static org.crossv.expressions.ExpressionEvaluator.NO_INSTANCE;

import org.crossv.expressions.Add;
import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.expressions.ExpressionEvaluator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpressionTests {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void evaluateExpression_UnrecognizedExpressiobByEvaluaror_EvaluationExceptionWithUnrecognizedExpressionMessageIsThrown()
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

	@Test
	public void getValueFromBadlyImplementedEvaluator_PredefinedExpression_EvaluationExceptionWithIncorrectEvaluationMessageIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Incorrect evaluation");

		Expression e = add(1, 2);
		ExpressionEvaluator badEvaluator = new ExpressionEvaluator(NO_INSTANCE,
				NO_CONTEXT) {
			@Override
			protected void evaluateAdd(Add expression) {
				eval(expression.getLeft());
				eval(expression.getRight());
				// not popping values from the stack
			}
		};
		e.evaluateWith(badEvaluator);
	}
}
