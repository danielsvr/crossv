package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CClass;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.RelationalOperationsContext;

public class InstanceOf extends RelationalExpression {
	public InstanceOf(Expression left, Expression right) {
		super(left, right);
		verifyOperands();
	}

	private void verifyOperands() {
		if (left.returnsPrimitiveType() || !right.isAssignableTo(CClass))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return CBoolean;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitInstanceOf(this);
	}

	public static InstanceOf parse(String text) {
		CrossVParser parser = createTextParser(text);
		RelationalOperationsContext context = parser.relationalOperations();
		return (InstanceOf) context.result;
	}

}
