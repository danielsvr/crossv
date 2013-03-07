package org.crossv.expressions;

import org.crossv.primitives.ArgumentNullException;

public abstract class UnaryExpression extends Expression {
	protected final Expression operand;

	public UnaryExpression(Expression operand) {
		if (operand == null)
			throw new ArgumentNullException("operand");
		this.operand = operand;
	}

	public Expression getOperand() {
		return operand;
	}
}
