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

		Number number;
		if (value instanceof Number
				&& ((number = (Number) value).byteValue() < 0
						|| number.shortValue() < 0 || number.intValue() < 0
						|| number.longValue() < 0 || number.floatValue() < 0
						|| number.doubleValue() < 0)) {
			out.print("(" + value.toString() + ")");
			return;
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

	protected void printBinaryExpression(BinaryExpression expression) {
		Expression left = expression.getLeft();
		Expression right = expression.getRight();

		print(left);
		print(" " + getOperatorString(expression) + " ");
		print(right);
	}

	protected void printCall(Call expression) {
		print(expression.getInstance());
		print("." + expression.getMethod().getName() + "(");
		boolean anyParameter = false;
		for (Expression parameter : expression.getParameters()) {
			if (anyParameter)
				print(", ");
			print(parameter);
			anyParameter = true;
		}
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
		if (expression instanceof Add)
			return "+";

		throw new ArgumentException("expression", format(
				"Unknown expression type {0}", expression.getClass().getName()));
	}
	
	private String getOperatorString(UnaryExpression expression) {
		if (expression instanceof Cast)
			return "(" + expression.getResultClass().getName() + ")";
		if (expression instanceof Negate)
			return "-";
		if (expression instanceof UnaryPlus)
			return "+";
		if (expression instanceof Not)
			return "!";
		
		throw new ArgumentException("expression", format(
				"Unknown expression type {0}", expression.getClass().getName()));
	}

	protected void printUnary(UnaryExpression expression) {
		print(getOperatorString(expression));
		print(expression.getOperand());
	}

	private static class PrivateExpressionVisitor implements ExpressionVisitor {
		private ExpressionWriter printer;

		public PrivateExpressionVisitor(ExpressionWriter printer) {
			this.printer = printer;
		}

		@Override
		public void visit(Expression expression) {
			if (expression instanceof Context) {
				printer.printContext((Context) expression);
				return;
			}
			if (expression instanceof Instance) {
				printer.printInstance((Instance) expression);
				return;
			}
			if (expression instanceof Constant) {
				printer.printConstant((Constant) expression);
				return;
			}
			if (expression instanceof BinaryExpression) {
				printer.printBinaryExpression((BinaryExpression) expression);
				return;
			}
			if (expression instanceof Call) {
				printer.printCall((Call) expression);
				return;
			}
			
			if (expression instanceof UnaryExpression) {
				printer.printUnary((UnaryExpression) expression);
				return;
			}
			
			printer.printError(expression);
		}
	}
}
