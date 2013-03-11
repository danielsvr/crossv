package org.crossv.expressions;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;
import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;

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

	public static final Object NO_CONTEXT = Missing.VALUE;
	public static final Object NO_INSTANCE = Missing.VALUE;
	private final Object context;
	private final Object instance;
	protected final Stack<Object> stack;
	private final ExpressionVisitor visitor;

	public ExpressionEvaluator(Object instance, Object context) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (context == null)
			throw new ArgumentNullException("context");
		this.instance = instance;
		this.context = context;
		visitor = new ExpressionEvaluationVisitor(this);
		stack = new Stack<Object>();
	}

	protected void eval(Expression expression) {
		expression.accept(visitor);
	}

	private EvaluationException evalError(RuntimeEvaluationException e) {
		String trail = " Please inspect cause for more details.";
		trail = e.getCause() != null ? trail : "";
		return new EvaluationException(e.getMessage() + trail, e.getCause());
	}

	public final void evaluate(Expression expression)
			throws EvaluationException {
		try {
			eval(expression);
		} catch (RuntimeEvaluationException e) {
			throw evalError(e);
		}
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

	protected void evaluateCoalesce(Coalesce expression) {
		evaluateConverableExpression(expression);
	}

	protected void evaluateConditional(Conditional expression) {
		eval(expression.getTest());
		Object popedValue = stack.pop();
		if (popedValue.equals(true))
			eval(expression.getIfTrue());
		else
			eval(expression.getIfFalse());
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

	private <E extends Expression> void evaluateConverableExpression(
			ConvertibleTo<E> expression) {
		E converted = expression.convert();
		eval(converted);
	}

	protected void evaluateDevide(Devide expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(Integer.class)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			stack.push(left / right);
		} else if (expression.isAssignableTo(Long.class)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left / right);
		} else if (expression.isAssignableTo(Float.class)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			stack.push(left / right);
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			stack.push(left / right);
		}
	}

	public void evaluateEqual(Equal expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		stack.push((leftPop == null && rightPop == null)
				|| (leftPop != null && leftPop.equals(rightPop)));
	}

	protected void evaluateGreaterThan(GreaterThan expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		Class<?> leftClass = expression.getLeft().getResultClass();
		Class<?> rightClass = expression.getRight().getResultClass();
		Class<?> promotion = getNumericPromotion(leftClass, rightClass);
		if (Integer.class.isAssignableFrom(promotion)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			stack.push(left > right);
		} else if (Long.class.isAssignableFrom(promotion)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left > right);
		} else if (Float.class.isAssignableFrom(promotion)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			stack.push(left > right);
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			stack.push(left > right);
		}
	}

	protected void evaluateGreaterThanOrEqual(GreaterThanOrEqual expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		Class<?> leftClass = expression.getLeft().getResultClass();
		Class<?> rightClass = expression.getRight().getResultClass();
		Class<?> promotion = getNumericPromotion(leftClass, rightClass);
		if (Integer.class.isAssignableFrom(promotion)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			stack.push(left >= right);
		} else if (Long.class.isAssignableFrom(promotion)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left >= right);
		} else if (Float.class.isAssignableFrom(promotion)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			stack.push(left >= right);
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			stack.push(left >= right);
		}
	}

	protected void evaluateInstance(Instance expression) {
		if (instance instanceof Missing) {
			String message = "Instance value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(instance);
	}

	protected void evaluateNotEqual(NotEqual expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();

		stack.push((leftPop == null && rightPop != null)
				|| (leftPop != null && !leftPop.equals(rightPop)));
	}

	protected void evaluateUnrecognizedExpresison(Expression expression) {
		Class<?> expressionClass = expression.getClass();
		String message = "Unrecognized expression of type {0}.";
		message = format(message, expressionClass.getName());
		throw new RuntimeEvaluationException(message);
	}

	public Object getValue() throws EvaluationException {
		if (stack.size() != 1)
			throw new EvaluationException("Incorrect evaluation.");
		return stack.pop();
	}

	protected void evaluateInstanceOf(InstanceOf expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Class<?> rightPop = (Class<?>) stack.pop();
		Object leftPop = stack.pop();
		stack.push(leftPop != null ? rightPop.isAssignableFrom(leftPop
				.getClass()) : false);
	}

	protected void evaluateOrElse(OrElse expression) {
		evaluateOr(expression);
	}

	private void evaluateOr(BinaryExpression expression) {
		eval(expression.getLeft());
		if (stack.peek().equals(false)) {
			stack.pop();
			eval(expression.getRight());
		}
	}

	protected void evaluateLeftShift(LeftShift expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(Integer.class)) {
			int left = ((Number) leftPop).intValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left << right);
		} else {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			stack.push(left << right);
		}
	}
}
