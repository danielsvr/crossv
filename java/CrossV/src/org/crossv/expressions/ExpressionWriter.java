package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CEnumeration;
import static org.crossv.primitives.ClassDescriptor.CIterable;
import static org.crossv.primitives.Iterables.toIterable;

import java.io.PrintWriter;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.MemberDescriptor;

public class ExpressionWriter {
	private final PrintWriter out;
	private final ExpressionVisitor visitor;

	public ExpressionWriter(PrintWriter printWriter) {
		if (printWriter == null)
			throw new ArgumentNullException("printWriter");
		out = printWriter;
		visitor = new ExpressionWriterVisitor(this);
	}

	public void print(Expression expression) {
		expression.accept(visitor);
	}

	protected void print(Object... expressions) {
		for (Object expression : expressions)
			if (expression instanceof Constant)
				printConstant((Constant) expression);
			else if (expression instanceof Expression)
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

	protected void printObject(Object expression) {
		if (expression == null) {
			out.print("null");
			return;
		}

		if (expression instanceof String) {
			out.print("\"" + expression.toString() + "\"");
			return;
		}

		if (expression instanceof Number) {
			Number number = (Number) expression;
			if (number.doubleValue() < 0) {
				out.print("(" + number.toString() + ")");
				return;
			}
		}

		if (expression instanceof Class) {
			out.print(((Class<?>) expression).getName());
			return;
		}

		out.print(expression.toString());
	}

	protected void printArray(Iterable<?> values) {
		boolean first = true;
		for (Object obj : values) {
			if (first)
				out.print("new " + obj.getClass().getName() + "[] { ");
			else
				out.print(", ");
			first &= false;
			printObject(obj);
		}
		out.print(" }");
	}

	protected void printConstant(Constant expression) {
		Object value = expression.getValue();

		if (value != null && expression.isAssignableTo(CIterable)) {
			printArray((Iterable<?>) value);
			return;
		}
		if (value != null
				&& (expression.isArray() || expression
						.isAssignableTo(CEnumeration))) {
			printArray(toIterable(value));
			return;
		}

		printObject(value);
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
		print("." + expression.getMethodName() + "(");
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
		Expression operand = expression.getOperand();
		if (operand instanceof Constant)
			print("!", operand);
		else
			print("!(", operand, ")");
	}

	protected void printConditional(ConditionalTernary expression) {
		print(expression.getTest(), " ? ", expression.getIfTrue(), " : ",
				expression.getIfFalse());
	}

	protected void printComplement(Complement expression) {
		print("~", expression.getOperand());
	}

	public void printSequenceLength(SequenceLength expression) {
		print(expression.getOperand(), ".length");
	}

	public void printSequenceIndex(SequenceIndex expression) {
		print(expression.getSequence(), "[", expression.getIndex(), "]");
	}

	public void printMemberAccess(MemberAccess expression) {
		MemberDescriptor member = expression.getMember();
		String name = member.getName();
		if (member.isMethod())
			name += "()";
		Expression instance = expression.getInstance();
		print(instance, ".", name);
	}

	public void printValidIf(ValidIf expression) {
		Expression scope = expression.getScope();
		Expression test = expression.getTest();
		Expression ifFalse = expression.getIfFalse();
		print(scope, " validif ", test, " else ", ifFalse);
	}

	public void printWarnIf(WarnIf expression) {
		Expression scope = expression.getScope();
		Expression test = expression.getTest();
		Expression ifFalse = expression.getIfFalse();
		print(scope, " warnif ", test, " then ", ifFalse);
	}

	public void printWhen(When expression) {
		Expression scope = expression.getScope();
		Expression[] evaluators = expression.getEvaluators();
		print("when ", scope, " [");
		for (int i = 0; i < evaluators.length; i++) {
			Expression evaluator = evaluators[i];
			if (i != 0)
				print(",");
			print(evaluator);
		}
		print("]");
	}
}
