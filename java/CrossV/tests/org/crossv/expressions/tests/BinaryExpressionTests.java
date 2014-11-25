package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;

import org.crossv.expressions.BinaryExpression;
import org.crossv.expressions.Expression;
import org.crossv.expressions.ExpressionVisitor;
import org.junit.Test;

public class BinaryExpressionTests {

	static class ExpressionMock extends BinaryExpression {
		public ExpressionMock(Expression left, Expression right) {
			super(left, right);
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
	public void createBinaryExpression_WithNullExpressionOnLeft_ThrowsIllegalArgumentException() {
		new ExpressionMock(null, constant(new Object()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createBinaryExpression_WithNullExpressionOnRight_ThrowsIllegalArgumentException() {
		new ExpressionMock(constant(new Object()), null);
	}
}
