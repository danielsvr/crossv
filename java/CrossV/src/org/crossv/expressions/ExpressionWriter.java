package org.crossv.expressions;

import static java.text.MessageFormat.format;

import java.io.PrintWriter;

import org.crossv.primitives.ArgumentException;
import org.crossv.primitives.ArgumentNullException;

public class ExpressionWriter {
	private final PrintWriter out;
	private final ExpressionVisitor visitor;

	public ExpressionWriter(PrintWriter printWriter) {
		if (printWriter == null)
			throw new ArgumentNullException("printWriter");
		out = printWriter;
		visitor = new PrivateExpressionVisitor(this);
	}

	public void print(Expression expression) {
		expression.accept(visitor);
	}

	protected void print(String expression) {
		out.print(expression);
	}

	protected void printConstant(Constant expression) {
		Object value = expression.getValue();
		if (value == null) {
			out.print("null");
			return;
		}

		if (value instanceof String) {
			out.print("\"" + value.toString() + "\"");
			return;
		}

		if (value instanceof Class) {
			out.print(((Class<?>) value).getName());
			return;
		}

		out.print(value.toString());
	}

	protected void printBinaryExpression(BinaryExpression expression) {
		Expression left = expression.getLeft();
		Expression right = expression.getRight();

		print("(");
		print(left);
		print(" " + getOperatorString(expression) + " ");
		print(right);
		print(")");
	}

	private String getOperatorString(BinaryExpression expression) {
		if (expression instanceof AndAlso)
			return "&&";
		if (expression instanceof Equal)
			return "==";
		if (expression instanceof GreaterThan)
			return ">";
		if (expression instanceof GreaterThanOrEqual)
			return ">=";
		if (expression instanceof GreaterThanOrEqual)
			return ">=";
		if (expression instanceof InstanceOf)
			return "instanceof";
		if (expression instanceof LessThan)
			return "<";
		if (expression instanceof LessThanOrEqual)
			return "<=";
		if (expression instanceof NotEqual)
			return "!=";
		if (expression instanceof OrElse)
			return "||";

		throw new ArgumentException("expression", format(
				"Unknown expression type {0}", expression.getClass().getName()));
	}

	private static class PrivateExpressionVisitor implements ExpressionVisitor {
		private ExpressionWriter printer;

		public PrivateExpressionVisitor(ExpressionWriter printer) {
			this.printer = printer;
		}

		@Override
		public void visit(Expression expression) {
			if (expression instanceof Constant) {
				printer.printConstant((Constant) expression);
				return;
			}
			if (expression instanceof BinaryExpression) {
				printer.printBinaryExpression((BinaryExpression) expression);
				return;
			}
		}
	}
}