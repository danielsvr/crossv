package org.crossv.expressions;

public interface ExpressionVisitor {

	void visit(Expression expression);

	void visitContext(Context expression);

	void visitInstance(Instance expression);

	void visitConstant(Constant expression);

	void visitBinary(BinaryExpression expression);

	void visitCall(Call expression);

	void visitUnary(UnaryExpression expression);

	void visitConditional(Conditional expression);
}
