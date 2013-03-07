package org.crossv.expressions;

import org.crossv.primitives.ArgumentNullException;

public class ExpressionEvaluator {

	public static final Object NO_INSTANCE = Missing.VALUE;
	public static final Object NO_CONTEXT = Missing.VALUE;
	private final ExpressionVisitor visitor;
	protected Object evaluatedValue;
	private final Object instance;
	private final Object context;

	public ExpressionEvaluator() {
		this(NO_INSTANCE, NO_CONTEXT);
	}

	public ExpressionEvaluator(Object instance, Object context) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (context == null)
			throw new ArgumentNullException("context");
		this.instance = instance;
		this.context = context;
		visitor = new PrivateExpressionVisitor(this);
	}

	private static class Missing {
		public static final Missing VALUE = new Missing();

		private Missing() {
		}
	}

	private static class RuntimeEvaluationException extends RuntimeException {
		private static final long serialVersionUID = 6163536626110997376L;
	}

	private static class PrivateExpressionVisitor extends
			ExpressionVisitorAdapter {
		private final ExpressionEvaluator evaluator;

		public PrivateExpressionVisitor(ExpressionEvaluator evaluator) {
			this.evaluator = evaluator;
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

	public Object getValue() {
		return evaluatedValue;
	}

	protected void evaluateInstance(Instance expression) {
		if (instance instanceof Missing)
			throw new RuntimeEvaluationException();
		evaluatedValue = instance;
	}

	protected void evaluateContext(Context expression) {
		if (context instanceof Missing)
			throw new RuntimeEvaluationException();
		evaluatedValue = context;
	}

	protected void evaluateConstant(Constant expression) {
		evaluatedValue = expression.getValue();
	}

	public void evaluate(Expression expression) throws EvaluationException {
		try {
			expression.accept(visitor);
		} catch (RuntimeEvaluationException e) {
			throw new EvaluationException();
		}
	}
}
