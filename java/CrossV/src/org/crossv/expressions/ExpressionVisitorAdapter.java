package org.crossv.expressions;

public class ExpressionVisitorAdapter implements ExpressionVisitor {

	@Override
	public void visit(Expression expression) {
	}

	@Override
	public void visitContext(Context expression) {
		visit(expression);
	}

	@Override
	public void visitInstance(Instance expression) {
		visit(expression);
	}

	@Override
	public void visitConstant(Constant expression) {
		visit(expression);
	}

	@Override
	public void visitBinary(BinaryExpression expression) {
		visit(expression);
	}

	@Override
	public void visitCall(Call expression) {
		visit(expression);
	}

	@Override
	public void visitUnary(UnaryExpression expression) {
		visit(expression);
	}

	@Override
	public void visitConditional(Conditional expression) {
		visit(expression);
	}
}
