package org.crossv.expressions;

public class BasicExpressionEvaluator extends ExpressionEvaluator {

	public BasicExpressionEvaluator(Object instance, Object context) {
		this(instance, context, new EvaluationOptions());
	}

	public BasicExpressionEvaluator() {
		this(ExpressionEvaluator.NO_INSTANCE, ExpressionEvaluator.NO_CONTEXT,
				new EvaluationOptions());
	}

	public BasicExpressionEvaluator(Object instance, Object context,
			EvaluationOptions options) {
		super(instance, context, options);
	}

	@Override
	public ExpressionEvaluator beginScope(Object instance, Object context) {
		return new BasicExpressionEvaluator(instance, context);
	}
}