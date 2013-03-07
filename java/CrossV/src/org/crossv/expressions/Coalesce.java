package org.crossv.expressions;

public class Coalesce extends BinaryExpression {

	private Expression innerExpression;

	public Coalesce(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
		Expression coalesceTest = notEqual(left, constant(null));
		innerExpression = conditional(coalesceTest, left, right);
	}

	private void verifyOperands() {
		//@formatter:off
		if (left.returnsPrimitiveType()
			|| right.returnsPrimitiveType()
			|| (!left.isAssignableTo(rightClass) && !right.isAssignableTo(leftClass)))
			throw illegalOperand();
		//@formatter:on
	}

	@Override
	public Class<?> getResultClass() {
		return innerExpression.getResultClass();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitCoalesce(this);
	}
}
