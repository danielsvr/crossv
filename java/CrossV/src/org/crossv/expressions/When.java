package org.crossv.expressions;

import static org.crossv.primitives.Iterables.addAllToList;

import java.util.ArrayList;
import java.util.List;

import org.crossv.Evaluator;
import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.ValidationsContext;

public class When extends Expression {

	private Expression scope;
	private Expression[] evaluators;

	public When(Expression scope, Expression[] evaluators) {
		this.scope = scope;
		this.evaluators = evaluators;
	}

	@Override
	public Class<?> getResultClass() {
		return Evaluator.class;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitWhen(this);
	}

	public Expression getScope() {
		return scope;
	}

	public Expression[] getEvaluators() {
		return evaluators;
	}

	public static Expression parse(String text) {
		CrossVParser parser = createTextParser(text);
		ValidationsContext context = parser.validations();
		AggregateWhenExpression result = new AggregateWhenExpression(
				context.result);
		return result;
	}

	private static class AggregateWhenExpression extends Expression {

		private Expression[] expressions;

		public AggregateWhenExpression(Expression[] expressions) {
			this.expressions = expressions;
		}

		@Override
		public Class<?> getResultClass() {
			return new Expression[0].getClass();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <E> E evaluateWith(ExpressionEvaluator evaluator)
				throws EvaluationException {
			List<Evaluator> values = new ArrayList<Evaluator>();
			for (Expression expression : expressions) {
				Iterable<Evaluator> value = expression.evaluateWith(evaluator);
				addAllToList(values, value);
			}

			return (E) values;
		}
	}
}
