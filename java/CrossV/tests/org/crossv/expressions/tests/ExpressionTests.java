package org.crossv.expressions.tests;

import static org.crossv.expressions.ExpressionEvaluator.NO_CONTEXT;
import static org.crossv.expressions.ExpressionEvaluator.NO_INSTANCE;
import static org.crossv.expressions.Expressions.add;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import junit.framework.AssertionFailedError;

import org.crossv.expressions.Add;
import org.crossv.expressions.BasicExpressionEvaluator;
import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.expressions.ExpressionEvaluator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpressionTests {

	public static class PrintStreamMock extends PrintStream {

		private boolean printWasCalled;

		public PrintStreamMock() {
			super(new OutputStream() {
				@Override
				public void write(int b) throws IOException {
				}
			});
		}

		@Override
		public void print(String s) {
			printWasCalled = true;
		}

		public void assertPrintWasCalled() {
			if (!printWasCalled) {
				String message = "print was not called";
				throw new AssertionFailedError(message);
			}
		}
	}

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
	public void trying_to_print_to_SystemOut() throws Exception {
		PrintStreamMock fakeWriter = new PrintStreamMock();
		Expression unknown = new Expression() {
			@Override
			public Class<?> getResultClass() {
				return null;
			}
		};

		System.setOut(fakeWriter);
		unknown.print();
		// fakeWriter.assertPrintWasCalled();
	}

	@Test
	public void tryingToPrintExpressionToANullPrintWriter_ThrowsIllegalArgumentException()
			throws Exception {
		exception.expect(IllegalArgumentException.class);

		Expression unknown = new Expression() {
			@Override
			public Class<?> getResultClass() {
				return null;
			}
		};

		unknown.print((PrintWriter) null);
	}

	@Test
	public void getValueFromBadlyImplementedEvaluator_PredefinedExpression_EvaluationExceptionWithIncorrectEvaluationMessageIsThrown()
			throws Exception {
		exception.expect(EvaluationException.class);
		exception.expectMessage("Incorrect evaluation");

		Expression e = add(1, 2);
		ExpressionEvaluator badEvaluator = new BasicExpressionEvaluator(
				NO_INSTANCE, NO_CONTEXT) {
			@Override
			public void evaluateAdd(Add expression) {
				eval(expression.getLeft());
				eval(expression.getRight());
				// not popping values from the stack
			}
		};
		e.evaluateWith(badEvaluator);
	}
}
