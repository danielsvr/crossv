package org.crossv.expressions;

import static java.text.MessageFormat.format;

import java.io.PrintWriter;

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

	protected void printError(Expression expression) {
		print("ERROR printing instance of {0}", expression.getClass());
	}

	protected void print(String expression) {
		out.print(expression);
	}

	protected void print(String expression, Object... formatArgs) {
		out.print(format(expression, formatArgs));
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

		if (value instanceof Number) {
			Number number = (Number) value;
			if (number.doubleValue() < 0) {
				out.print("(" + value.toString() + ")");
				return;
			}
		}

		if (value instanceof Class) {
			out.print(((Class<?>) value).getName());
			return;
		}

		out.print(value.toString());
	}

	protected void printContext(Context expression) {
		out.print("context");
	}

	protected void printInstance(Instance expression) {
		out.print("obj");
	}

	protected void printBinary(BinaryExpression expression) {
		Expression left = expression.getLeft();
		Expression right = expression.getRight();

		print(left);
		print(" " + expression.getOperatorString() + " ");
		print(right);
	}

	protected void printCall(Call expression) {
		print(expression.getInstance());
		print("." + expression.getMethod().getName() + "(");
		boolean anyParameter = false;
		Expression[] parameters = expression.getParameters();
		for (Expression parameter : parameters) {
			if (anyParameter)
				print(", ");
			print(parameter);
			anyParameter = true;
		}
		print(")");
	}

	protected void printUnary(UnaryExpression expression) {
		print(expression.getOperatorString());
		print(expression.getOperand());
	}

	protected void printConditional(Conditional expression) {
		print(expression.getTest());
		print(" ? ");
		print(expression.getIfTrue());
		print(" : ");
		print(expression.getIfFalse());
	}

	private static class PrivateExpressionVisitor extends
			ExpressionVisitorAdapter {
		private ExpressionWriter printer;

		public PrivateExpressionVisitor(ExpressionWriter printer) {
			this.printer = printer;
		}

		@Override
		public void visit(Expression expression) {
			printer.printError(expression);
		}

		@Override
		public void visitContext(Context expression) {
			printer.printContext(expression);
		}

		@Override
		public void visitInstance(Instance expression) {
			printer.printInstance(expression);
		}

		@Override
		public void visitConstant(Constant expression) {
			printer.printConstant(expression);
		}

		@Override
		public void visitBinary(BinaryExpression expression) {
			printer.printBinary(expression);
		}

		@Override
		public void visitCall(Call expression) {
			printer.printCall(expression);
		}

		@Override
		public void visitUnary(UnaryExpression expression) {
			printer.printUnary(expression);
		}

		@Override
		public void visitConditional(Conditional expression) {
			printer.printConditional(expression);
		}
	}
}
