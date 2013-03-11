package org.crossv.expressions;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ConvertibleTo;

public class ExpressionEvaluator {

	private static class Missing {
		public static final Missing VALUE = new Missing();

		private Missing() {
		}
	}

	private static class PrivateExpressionVisitor extends
			ExpressionVisitorAdapter {
		private final ExpressionEvaluator evaluator;

		public PrivateExpressionVisitor(ExpressionEvaluator evaluator) {
			this.evaluator = evaluator;
		}

		@Override
		public void visit(Expression expression) {
			evaluator.evaluateUnrecognizedExpresison(expression);
		}

		@Override
		public void visitAdd(Add expression) {
			evaluator.evaluateAdd(expression);
		}

		@Override
		public void visitAnd(And expression) {
			evaluator.evaluateAnd(expression);
		}

		@Override
		public void visitAndAlso(AndAlso expression) {
			evaluator.evaluateAndAlso(expression);
		}

		@Override
		public void visitCall(Call expression) {
			evaluator.evaluateCall(expression);
		}

		@Override
		public void visitCast(Cast expression) {
			evaluator.evaluateCast(expression);
		}

		@Override
		public void visitConstant(Constant expression) {
			evaluator.evaluateConstant(expression);
		}

		@Override
		public void visitContext(Context expression) {
			evaluator.evaluateContext(expression);
		}

		@Override
		public void visitInstance(Instance expression) {
			evaluator.evaluateInstance(expression);
		}

		@Override
		public void visitConditional(Conditional expression) {
			evaluator.evaluateConditional(expression);
		}

		@Override
		public void visitCoalesce(Coalesce expression) {
			evaluator.evaluateCoalesce(expression);
		}

		@Override
		public void visitNotEqual(NotEqual expression) {
			evaluator.evaluateNotEqual(expression);
		}
	}

	private static class RuntimeEvaluationException extends RuntimeException {
		private static final long serialVersionUID = 6163536626110997376L;

		public RuntimeEvaluationException(Throwable cause) {
			this(cause.getMessage(), cause);
		}

		public RuntimeEvaluationException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	public static final Object NO_INSTANCE = Missing.VALUE;
	public static final Object NO_CONTEXT = Missing.VALUE;
	private final ExpressionVisitor visitor;
	protected final Stack<Object> stack;
	private final Object instance;
	private final Object context;

	public ExpressionEvaluator(Object instance, Object context) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (context == null)
			throw new ArgumentNullException("context");
		this.instance = instance;
		this.context = context;
		visitor = new PrivateExpressionVisitor(this);
		stack = new Stack<Object>();
	}

	protected void evaluateNotEqual(NotEqual expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();

		stack.push((leftPop == null && rightPop != null)
				|| (leftPop != null && !leftPop.equals(rightPop)));
	}

	protected void evaluateCoalesce(Coalesce expression) {
		evaluateConverableExpression(expression);
	}

	private <E extends Expression> void evaluateConverableExpression(
			ConvertibleTo<E> expression) {
		E converted = expression.convert();
		eval(converted);
	}

	protected void evaluateConditional(Conditional expression) {
		eval(expression.getTest());
		Object popedValue = stack.pop();
		if (popedValue.equals(true))
			eval(expression.getIfTrue());
		else
			eval(expression.getIfFalse());
	}

	protected void evaluateUnrecognizedExpresison(Expression expression) {
		throw new RuntimeEvaluationException("Unrecognized expression.", null);
	}

	protected void eval(Expression expression) {
		expression.accept(visitor);
	}

	public final void evaluate(Expression expression)
			throws EvaluationException {
		try {
			eval(expression);
		} catch (RuntimeEvaluationException e) {
			throw evalError(e);
		}
	}

	private EvaluationException evalError(RuntimeEvaluationException e) {
		String trail = " Please inspect cause for more details.";
		trail = e.getCause() != null ? trail : "";
		return new EvaluationException(e.getMessage() + trail, e.getCause());
	}

	protected void evaluateAdd(Add expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(Integer.class)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			stack.push(left + right);
		} else if (expression.isAssignableTo(Long.class)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left + right);
		} else if (expression.isAssignableTo(Float.class)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			stack.push(left + right);
		} else if (expression.isAssignableTo(Double.class)) {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			stack.push(left + right);
		} else {
			//@formatter:off
			String left = leftPop == null 
					? "null" : leftPop.toString();
			String right = rightPop == null 
					? "null" : rightPop.toString();
			stack.push(left + right);
			//@formatter:on
		}
	}

	protected void evaluateAnd(And expression) {
		if (expression.isAssignableTo(Boolean.class)) {
			evaluateAnd((BinaryExpression) expression);
			return;
		}

		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(Integer.class)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			stack.push(left & right);
		} else {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left & right);
		}
	}

	private void evaluateAnd(BinaryExpression expression) {
		eval(expression.getLeft());
		if (stack.peek().equals(true)) {
			stack.pop();
			eval(expression.getRight());
		}
	}

	protected void evaluateAndAlso(AndAlso expression) {
		evaluateAnd(expression);
	}

	protected void evaluateCall(Call expression) {
		eval(expression.getInstance());
		Object instance = stack.pop();
		Expression[] paramExpressions = expression.getParameters();
		Object[] params = new Object[paramExpressions.length];
		for (int i = 0; i < params.length; i++) {
			eval(paramExpressions[i]);
			params[i] = stack.pop();
		}
		try {
			stack.push(expression.getMethod().invoke(instance, params));
		} catch (IllegalAccessException e) {
			throw new RuntimeEvaluationException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeEvaluationException(e);
		}
	}

	protected void evaluateCast(Cast expression) {
		eval(expression.getOperand());
		Class<?> resultClass = expression.getResultClass();
		Class<?> transformedResultClass;
		transformedResultClass = transformToTypeIfPrimitive(resultClass);
		char chr = 0;
		Object popedValue = stack.pop();
		boolean isChr = popedValue instanceof Character;
		Number no = null;
		boolean isNo = popedValue instanceof Number;
		boolean isBool = popedValue instanceof Boolean;

		if ((isChr || isNo) && transformedResultClass.equals(Boolean.TYPE)) {
			Class<?> evaluatedClass = popedValue.getClass();
			evaluatedClass = transformToTypeIfPrimitive(evaluatedClass);
			String message = "Cannot cast a {0} value to boolean.";
			message = format(message, evaluatedClass.getName());
			Throwable cause = new ClassCastException(message);
			throw new RuntimeEvaluationException(cause);
		}

		if (isChr)
			chr = ((Character) popedValue).charValue();
		if (isNo)
			no = (Number) popedValue;

		if ((isChr || isNo) && transformedResultClass.equals(Character.TYPE))
			stack.push(isChr ? (char) chr : (char) no.intValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Byte.TYPE))
			stack.push(isChr ? (byte) chr : no.byteValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Short.TYPE))
			stack.push(isChr ? (short) chr : no.shortValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Integer.TYPE))
			stack.push(isChr ? (int) chr : no.intValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Long.TYPE))
			stack.push(isChr ? (long) chr : no.longValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Float.TYPE))
			stack.push(isChr ? (float) chr : no.floatValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Double.TYPE))
			stack.push(isChr ? (double) chr : no.doubleValue());
		else if (isBool && transformedResultClass.equals(Character.TYPE)
				|| Number.class.isAssignableFrom(resultClass)) {
			String message = "Cannot cast a boolean value to {0}.";
			message = format(message, transformedResultClass.getName());
			Throwable cause = new ClassCastException(message);
			throw new RuntimeEvaluationException(cause);
		} else if (isBool && transformedResultClass.equals(Boolean.TYPE)) {
			stack.push(popedValue);
		} else
			try {
				stack.push(transformedResultClass.cast(popedValue));
			} catch (ClassCastException e) {
				Class<?> evaluatedClass = popedValue.getClass();
				String message = "Cannot cast a {0} value to {1}.";
				message = format(message, evaluatedClass.getName(),
						transformedResultClass.getName());
				throw new RuntimeEvaluationException(message, e);
			}
	}

	protected void evaluateConstant(Constant expression) {
		stack.push(expression.getValue());
	}

	protected void evaluateContext(Context expression) {
		if (context instanceof Missing) {
			String message = "Context value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(context);
	}

	protected void evaluateInstance(Instance expression) {
		if (instance instanceof Missing) {
			String message = "Instance value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(instance);
	}

	public Object getValue() throws EvaluationException {
		if (stack.size() != 1)
			throw new EvaluationException("Incorrect evaluation.");
		return stack.pop();
	}
}
