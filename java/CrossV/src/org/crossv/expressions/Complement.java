package org.crossv.expressions;

import static org.crossv.primitives.ClassDescriptor.CByte;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CLong;
import static org.crossv.primitives.ClassDescriptor.CShort;

import org.crossv.parsing.grammars.antlr4.CrossVParser;
import org.crossv.parsing.grammars.antlr4.CrossVParser.UnaryOperationsContext;
import org.crossv.primitives.ClassDescriptor;

public class Complement extends UnaryExpression {
	private Class<?> resultClass;

	public Complement(Expression operand) {
		super(operand);
		verifyOperand();
		if (operand.isAssignableTo(ClassDescriptor.CLong))
			resultClass = CLong;
		else
			resultClass = CInteger;
	}

	private void verifyOperand() {
		if (!operand.isAssignableToAny(CByte, CShort, CInteger, CLong))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitComplement(this);
	}

	public static Complement parse(String text) {
		CrossVParser parser = createTextParser(text);
		UnaryOperationsContext context = parser.unaryOperations();
		return (Complement) context.result;
	}
}
