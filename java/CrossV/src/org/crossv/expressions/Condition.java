package org.crossv.expressions;

import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.ArgumentException;

public class Condition extends BinaryOperation {

	private static List<ExpressionType> validOperators;
	static {
		validOperators = new ArrayList<ExpressionType>();
		validOperators.add(ExpressionType.AND_ALSO);
		validOperators.add(ExpressionType.EQUAL);
		validOperators.add(ExpressionType.GREATER_THAN);
		validOperators.add(ExpressionType.GREATER_THAN_OR_EQUAL);
		validOperators.add(ExpressionType.LEFT_SHIFT);
		validOperators.add(ExpressionType.LESS_THAN);
		validOperators.add(ExpressionType.LESS_THAN_OR_EQUAL);
		validOperators.add(ExpressionType.NOT_EQUAL);
		validOperators.add(ExpressionType.OR_ELSE);
		validOperators.add(ExpressionType.INSTANCE_OF);
	}

	private ExpressionType operator;

	public Condition(Expression left, Expression right, ExpressionType operator) {
		super(left, right);
		if (!validOperators.contains(operator))
			throw new ArgumentException("operator",
					"The provided operator is not applicable to boolean.");
		this.operator = operator;
	}

	@Override
	public ExpressionType getType() {
		return operator;
	}
}
