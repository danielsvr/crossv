package org.crossv.expressions;

import static org.crossv.primitives.Iterables.empty;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public class ExpressionBasedEvaluator implements Evaluator {

	private Class<?> objClass;
	private Class<?> contextClass;
	private Expression descriptorExp;
	private ExpressionEvaluationScope evaluationScope;

	public ExpressionBasedEvaluator(Class<?> objClass, Class<?> contextClass,
			ExpressionEvaluationScope evaluationScope, Expression descriptorExp) {
		this.objClass = objClass;
		this.contextClass = contextClass;
		this.evaluationScope = evaluationScope;
		this.descriptorExp = descriptorExp;
	}

	@Override
	public Iterable<Evaluation> evaluate(Object obj, Object context) {
		EvaluationDescriptor descriptor;
		ExpressionEvaluator scope;
		scope = evaluationScope.beginScope(obj, context);
		try {
			descriptor = descriptorExp.evaluateWith(scope);
		} catch (EvaluationException e) {
			throw new RuntimeEvaluationException(e);
		}

		Expression test = descriptor.getTest();
		try {
			boolean result = test.evaluateWith(scope);
			if (result) {
				if (descriptor.isWarning()) {
					String ifTrueMessage = descriptor.getIfTrue().evaluateWith(scope);
					return Evaluation.warning(ifTrueMessage);
				}
			} else {
				if (descriptor.isValidation()) {
					String ifFalseMessage = descriptor.getIfFalse().evaluateWith(scope);
					return Evaluation.fault(ifFalseMessage);
				}
			}
		} catch (EvaluationException e) {
			return Evaluation.fault(e);
		}
		return empty();
	}

	@Override
	public Class<?> getInstanceClass() {
		return objClass;
	}

	@Override
	public Class<?> getContextClass() {
		return contextClass;
	}
}
