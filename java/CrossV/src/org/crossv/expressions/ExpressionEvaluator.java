package org.crossv.expressions;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;

import java.lang.reflect.InvocationTargetException;

import org.crossv.primitives.ArgumentNullException;

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
	protected Object evaluatedValue;
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
	}

	protected void eval(Expression expression) {
		expression.accept(visitor);
	}

	public void evaluate(Expression expression) throws EvaluationException {
		try {
			eval(expression);
		} catch (RuntimeEvaluationException e) {
			throw new EvaluationException(e);
		}
	}

	protected void evaluateAdd(Add expression) {
		eval(expression.getLeft());
		Object accumulator = evaluatedValue;
		eval(expression.getRight());

		if (expression.isAssignableTo(Integer.class)) {
			int left = ((Number) accumulator).intValue();
			int right = ((Number) evaluatedValue).intValue();
			evaluatedValue = left + right;
		} else if (expression.isAssignableTo(Long.class)) {
			long left = ((Number) accumulator).longValue();
			long right = ((Number) evaluatedValue).longValue();
			evaluatedValue = left + right;
		} else if (expression.isAssignableTo(Float.class)) {
			float left = ((Number) accumulator).floatValue();
			float right = ((Number) evaluatedValue).floatValue();
			evaluatedValue = left + right;
		} else if (expression.isAssignableTo(Double.class)) {
			double left = ((Number) accumulator).doubleValue();
			double right = ((Number) evaluatedValue).doubleValue();
			evaluatedValue = left + right;
		} else {
			//@formatter:off
			String left = accumulator == null 
					? "null" : accumulator.toString();
			String right = evaluatedValue == null 
					? "null" : evaluatedValue.toString();
			evaluatedValue = left + right;
			//@formatter:on
		}
	}

	protected void evaluateAnd(And expression) {
		if (expression.isAssignableTo(Boolean.class)) {
			evaluateAnd((BinaryExpression) expression);
			return;
		}

		eval(expression.getLeft());
		Object accumulator = evaluatedValue;
		eval(expression.getRight());

		if (expression.isAssignableTo(Integer.class)) {
			int left = ((Number) accumulator).intValue();
			int right = ((Number) evaluatedValue).intValue();
			evaluatedValue = left & right;
		} else {
			long left = ((Number) accumulator).longValue();
			long right = ((Number) evaluatedValue).longValue();
			evaluatedValue = left & right;
		}
	}

	private void evaluateAnd(BinaryExpression expression) {
		eval(expression.getLeft());
		if (evaluatedValue.equals(false))
			return;
		eval(expression.getRight());
	}

	protected void evaluateAndAlso(AndAlso expression) {
		evaluateAnd(expression);
	}

	protected void evaluateCall(Call expression) {
		eval(expression.getInstance());
		Object instance = evaluatedValue;
		Expression[] paramExpressions = expression.getParameters();
		Object[] params = new Object[paramExpressions.length];
		for (int i = 0; i < params.length; i++) {
			eval(paramExpressions[i]);
			params[i] = evaluatedValue;
		}
		try {
			evaluatedValue = expression.getMethod().invoke(instance, params);
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
		boolean isChr = evaluatedValue instanceof Character;
		Number no = null;
		boolean isNo = evaluatedValue instanceof Number;
		boolean isBool = evaluatedValue instanceof Boolean;

		if ((isChr || isNo) && transformedResultClass.equals(Boolean.TYPE)) {
			Class<?> evaluatedClass = evaluatedValue.getClass();
			evaluatedClass = transformToTypeIfPrimitive(evaluatedClass);
			String message = "Cannot cast a {0} value to boolean.";
			message = format(message, evaluatedClass.getName());
			Throwable cause = new ClassCastException(message);
			throw new RuntimeEvaluationException(cause);
		}

		if (isChr)
			chr = ((Character) evaluatedValue).charValue();
		if (isNo)
			no = (Number) evaluatedValue;

		if ((isChr || isNo) && transformedResultClass.equals(Character.TYPE))
			evaluatedValue = isChr ? (char) chr : (char) no.intValue();
		else if ((isChr || isNo) && transformedResultClass.equals(Byte.TYPE))
			evaluatedValue = isChr ? (byte) chr : no.byteValue();
		else if ((isChr || isNo) && transformedResultClass.equals(Short.TYPE))
			evaluatedValue = isChr ? (short) chr : no.shortValue();
		else if ((isChr || isNo) && transformedResultClass.equals(Integer.TYPE))
			evaluatedValue = isChr ? (int) chr : no.intValue();
		else if ((isChr || isNo) && transformedResultClass.equals(Long.TYPE))
			evaluatedValue = isChr ? (long) chr : no.longValue();
		else if ((isChr || isNo) && transformedResultClass.equals(Float.TYPE))
			evaluatedValue = isChr ? (float) chr : no.floatValue();
		else if ((isChr || isNo) && transformedResultClass.equals(Double.TYPE))
			evaluatedValue = isChr ? (double) chr : no.doubleValue();
		else if (isBool && transformedResultClass.equals(Character.TYPE)
				|| Number.class.isAssignableFrom(resultClass)) {
			String message = "Cannot cast a boolean value to {0}.";
			message = format(message, transformedResultClass.getName());
			Throwable cause = new ClassCastException(message);
			throw new RuntimeEvaluationException(cause);
		} else if (isBool && transformedResultClass.equals(Boolean.TYPE))
			return;
		else
			try {
				evaluatedValue = transformedResultClass.cast(evaluatedValue);
			} catch (ClassCastException e) {
				Class<?> evaluatedClass = evaluatedValue.getClass();
				String message = "Cannot cast a {0} value to {1}.";
				message = format(message, evaluatedClass.getName(),
						transformedResultClass.getName());
				throw new RuntimeEvaluationException(message, e);
			}
	}

	protected void evaluateConstant(Constant expression) {
		evaluatedValue = expression.getValue();
	}

	protected void evaluateContext(Context expression) {
		if (context instanceof Missing) {
			String message = "Context value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		evaluatedValue = context;
	}

	protected void evaluateInstance(Instance expression) {
		if (instance instanceof Missing) {
			String message = "Instance value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		evaluatedValue = instance;
	}

	public Object getValue() {
		return evaluatedValue;
	}
}
