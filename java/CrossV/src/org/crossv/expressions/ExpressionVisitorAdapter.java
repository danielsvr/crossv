package org.crossv.expressions;

public class ExpressionVisitorAdapter implements ExpressionVisitor {

	@Override
	public void visit(Expression expression) {
	}

	@Override
	public void visitContext(Context expression) {
	}

	@Override
	public void visitInstance(Instance expression) {
	}

	@Override
	public void visitConstant(Constant expression) {
	}

	@Override
	public void visitBinary(BinaryExpression expression) {
	}

	@Override
	public void visitCall(Call expression) {
	}

	@Override
	public void visitUnary(UnaryExpression expression) {
	}
}
