package org.crossv.expressions;

import static org.crossv.primitives.Iterables.empty;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public class ExpressionBasedEvaluator implements Evaluator {

	private Class<?> objClass;
	private Class<?> contextClass;
	private Expression descriptorExp;

	public ExpressionBasedEvaluator(Class<?> objClass, Class<?> contextClass,
			Expression descriptorExp) {
		this.objClass = objClass;
		this.contextClass = contextClass;
		this.descriptorExp = descriptorExp;
	}

	@Override
	public Iterable<Evaluation> evaluate(Object obj, Object context) {
		EvaluationDescriptor descriptor;
		try {
			descriptor = descriptorExp.evaluate(obj, context);
		} catch (EvaluationException e) {
			throw new RuntimeEvaluationException(e);
		}

		Expression test = descriptor.getTest();
		try {
			boolean result = test.evaluate(obj, context);
			if (result) {
				if (descriptor.isWarning()) {
					String ifTrueMessage = descriptor.getIfTrueMessage();
					return Evaluation.warning(ifTrueMessage);
				}
			} else {
				if (descriptor.isValidation()) {
					String ifFalseMessage = descriptor.getIfFalseMessage();
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
