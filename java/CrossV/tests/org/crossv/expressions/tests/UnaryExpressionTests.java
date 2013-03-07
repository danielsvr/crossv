package org.crossv.expressions.tests;

import org.crossv.expressions.Expression;
import org.crossv.expressions.ExpressionVisitor;
import org.crossv.expressions.UnaryExpression;
import org.junit.Test;

public class UnaryExpressionTests {

	static class ExpressionMock extends UnaryExpression {

		public ExpressionMock(Expression operand) {
			super(operand);
		}

		@Override
		public Class<?> getResultClass() {
			return null;
		}

		@Override
		public void accept(ExpressionVisitor visitor) {
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void createUnaryExpression_WithNullOperand_ThrowsIllegalArgumentException() {
		new ExpressionMock(null);
	}
}
