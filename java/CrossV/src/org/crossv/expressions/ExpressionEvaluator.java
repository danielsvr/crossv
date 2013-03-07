package org.crossv.expressions;

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
			throw new EvaluationException();
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
			throw new RuntimeEvaluationException();
		} catch (InvocationTargetException e) {
			throw new RuntimeEvaluationException();
		}
	}

	protected void evaluateCast(Cast expression) {
		eval(expression.getOperand());
		Class<?> resultClass = expression.getResultClass();
		// Class<?> evaluatedClass = evaluatedValue != null ? evaluatedValue
		// .getClass() : null;
		if (evaluatedValue instanceof Character) {
			char v = ((Character) evaluatedValue).charValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}

		if (evaluatedValue instanceof Byte) {
			byte v = ((Number) evaluatedValue).byteValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}

		if (evaluatedValue instanceof Short) {
			short v = ((Number) evaluatedValue).shortValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}

		if (evaluatedValue instanceof Integer) {
			int v = ((Number) evaluatedValue).intValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}

		if (evaluatedValue instanceof Long) {
			long v = ((Number) evaluatedValue).longValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}

		if (evaluatedValue instanceof Float) {
			float v = ((Number) evaluatedValue).floatValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}

		if (evaluatedValue instanceof Double) {
			double v = ((Number) evaluatedValue).doubleValue();
			if (resultClass.equals(Boolean.class)) {
				throw new RuntimeEvaluationException();
			}
			if (resultClass.equals(Character.class)) {
				evaluatedValue = (char) v;
				return;
			}
			if (resultClass.equals(Byte.class)) {
				evaluatedValue = (byte) v;
				return;
			}
			if (resultClass.equals(Short.class)) {
				evaluatedValue = (short) v;
				return;
			}
			if (resultClass.equals(Integer.class)) {
				evaluatedValue = (int) v;
				return;
			}
			if (resultClass.equals(Long.class)) {
				evaluatedValue = (long) v;
				return;
			}
			if (resultClass.equals(Float.class)) {
				evaluatedValue = (float) v;
				return;
			}
			if (resultClass.equals(Double.class)) {
				evaluatedValue = (double) v;
				return;
			}
		}
		if (evaluatedValue instanceof Boolean) {
			if (resultClass.equals(Character.class)
					|| Number.class.isAssignableFrom(resultClass))
				throw new RuntimeEvaluationException();
			if (resultClass.equals(Boolean.class)) {
				return;
			}
		}
		evaluatedValue = resultClass.cast(evaluatedValue);
	}

	protected void evaluateConstant(Constant expression) {
		evaluatedValue = expression.getValue();
	}

	protected void evaluateContext(Context expression) {
		if (context instanceof Missing)
			throw new RuntimeEvaluationException();
		evaluatedValue = context;
	}

	protected void evaluateInstance(Instance expression) {
		if (instance instanceof Missing)
			throw new RuntimeEvaluationException();
		evaluatedValue = instance;
	}

	public Object getValue() {
		return evaluatedValue;
	}
}
