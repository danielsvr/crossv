package org.crossv.expressions;

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

	protected void print(Object... expressions) {
		for (Object expression : expressions)
			if (expression instanceof Expression)
				print((Expression) expression);
			else
				print(expression.toString());
	}

	protected void printError(Expression expression) {
		print("ERROR printing instance of ", expression.getClass());
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

	protected void printAdd(Add expression) {
		print(expression.getLeft(), " + ", expression.getRight());
	}

	protected void printAnd(And expression) {
		print(expression.getLeft(), " & ", expression.getRight());
	}

	protected void printInstanceOf(InstanceOf expression) {
		print(expression.getLeft(), " instanceof ", expression.getRight());
	}

	protected void printAndAlso(AndAlso expression) {
		print(expression.getLeft(), " && ", expression.getRight());
	}

	protected void printOrElse(OrElse expression) {
		print(expression.getLeft(), " || ", expression.getRight());
	}

	protected void printGreaterThan(GreaterThan expression) {
		print(expression.getLeft(), " > ", expression.getRight());
	}

	protected void printGreaterThanOrEqual(GreaterThanOrEqual expression) {
		print(expression.getLeft(), " >= ", expression.getRight());
	}

	protected void printLessThan(LessThan expression) {
		print(expression.getLeft(), " < ", expression.getRight());
	}

	protected void printLessThanOrEqual(LessThanOrEqual expression) {
		print(expression.getLeft(), " <= ", expression.getRight());
	}

	protected void printEqual(Equal expression) {
		print(expression.getLeft(), " == ", expression.getRight());
	}

	protected void printNotEqual(NotEqual expression) {
		print(expression.getLeft(), " != ", expression.getRight());
	}

	protected void printDevide(Devide expression) {
		print(expression.getLeft(), " / ", expression.getRight());
	}

	protected void printModulo(Modulo expression) {
		print(expression.getLeft(), " % ", expression.getRight());
	}

	protected void printMultiply(Multiply expression) {
		print(expression.getLeft(), " * ", expression.getRight());
	}

	protected void printSubtract(Subtract expression) {
		print(expression.getLeft(), " - ", expression.getRight());
	}

	protected void printRightShift(RightShift expression) {
		print(expression.getLeft(), " >> ", expression.getRight());
	}

	protected void printCoalesce(Coalesce expression) {
		print(expression.getLeft(), " ?? ", expression.getRight());
	}

	protected void printLeftShift(LeftShift expression) {
		print(expression.getLeft(), " << ", expression.getRight());
	}

	protected void printOr(Or expression) {
		print(expression.getLeft(), " | ", expression.getRight());
	}

	protected void printXor(Xor expression) {
		print(expression.getLeft(), " ^ ", expression.getRight());
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

	protected void printCast(Cast expression) {
		print("(", expression.getResultClass().getName(), ")",
				expression.getOperand());
	}

	protected void printNegate(Negate expression) {
		print("-", expression.getOperand());
	}

	protected void printUnaryPlus(UnaryPlus expression) {
		print("+", expression.getOperand());
	}

	protected void printNot(Not expression) {
		print("!", expression.getOperand());
	}

	protected void printConditional(Conditional expression) {
		print(expression.getTest(), " ? ", expression.getIfTrue(), " : ",
				expression.getIfFalse());
	}

	private static class PrivateExpressionVisitor extends
			ExpressionVisitorAdapter {
		@Override
		public void visitCast(Cast expression) {
			printer.printCast(expression);
		}

		@Override
		public void visitNegate(Negate expression) {
			printer.printNegate(expression);
		}

		@Override
		public void visitNot(Not expression) {
			printer.printNot(expression);
		}

		@Override
		public void visitUnaryPlus(UnaryPlus expression) {
			printer.printUnaryPlus(expression);
		}

		@Override
		public void visitAdd(Add expression) {
			printer.printAdd(expression);
		}

		@Override
		public void visitAnd(And expression) {
			printer.printAnd(expression);
		}

		@Override
		public void visitInstanceOf(InstanceOf expression) {
			printer.printInstanceOf(expression);
		}

		@Override
		public void visitAndAlso(AndAlso expression) {
			printer.printAndAlso(expression);
		}

		@Override
		public void visitOrElse(OrElse expression) {
			printer.printOrElse(expression);
		}

		@Override
		public void visitGreaterThan(GreaterThan expression) {
			printer.printGreaterThan(expression);
		}

		@Override
		public void visitGreaterThanOrEqual(GreaterThanOrEqual expression) {
			printer.printGreaterThanOrEqual(expression);
		}

		@Override
		public void visitLessThan(LessThan expression) {
			printer.printLessThan(expression);
		}

		@Override
		public void visitLessThanOrEqual(LessThanOrEqual expression) {
			printer.printLessThanOrEqual(expression);
		}

		@Override
		public void visitEqual(Equal expression) {
			printer.printEqual(expression);
		}

		@Override
		public void visitNotEqual(NotEqual expression) {
			printer.printNotEqual(expression);
		}

		@Override
		public void visitDevide(Devide expression) {
			printer.printDevide(expression);
		}

		@Override
		public void visitModulo(Modulo expression) {
			printer.printModulo(expression);
		}

		@Override
		public void visitMultiply(Multiply expression) {
			printer.printMultiply(expression);
		}

		@Override
		public void visitSubtract(Subtract expression) {
			printer.printSubtract(expression);
		}

		@Override
		public void visitRightShift(RightShift expression) {
			printer.printRightShift(expression);
		}

		@Override
		public void visitCoalesce(Coalesce expression) {
			printer.printCoalesce(expression);
		}

		@Override
		public void visitLeftShift(LeftShift expression) {
			printer.printLeftShift(expression);
		}

		@Override
		public void visitOr(Or expression) {
			printer.printOr(expression);
		}

		@Override
		public void visitXor(Xor expression) {
			printer.printXor(expression);
		}

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
		public void visitCall(Call expression) {
			printer.printCall(expression);
		}

		@Override
		public void visitConditional(Conditional expression) {
			printer.printConditional(expression);
		}
	}
}
