package org.crossv.expressions;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.notEqual;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.ExpressionContext;
import org.crossv.primitives.ConvertibleTo;

public class Coalesce extends BinaryExpression implements
		ConvertibleTo<ConditionalTernary> {

	private ConditionalTernary innerExpression;

	public Coalesce(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		Expression coalesceTest = notEqual(left, constant(null));
		innerExpression = new ConditionalTernary(coalesceTest, left, right);
	}

	private void verifyOperands() {
		// @formatter:off
		if (left.returnsPrimitiveType()
				|| right.returnsPrimitiveType()
				|| (!left.isAssignableTo(rightClass) && !right
						.isAssignableTo(leftClass)))
			throw illegalOperand();
		// @formatter:on
	}

	@Override
	public Class<?> getResultClass() {
		return innerExpression.getResultClass();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitCoalesce(this);
	}

	@Override
	public ConditionalTernary convert() {
		return innerExpression;
	}

	public static Coalesce parse(String text) {
		CrossVParser parser = createTextParser(text);
		ExpressionContext context = parser.expression();
		return (Coalesce) context.result;
	}
}
