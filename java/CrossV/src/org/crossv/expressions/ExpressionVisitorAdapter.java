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
	public void visitCall(Call expression) {
		visit(expression);
	}

	@Override
	public void visitConditional(ConditionalTernaryExpression expression) {
		visit(expression);
	}

	@Override
	public void visitAdd(Add expression) {
		visit(expression);
	}

	@Override
	public void visitAnd(And expression) {
		visit(expression);
	}

	@Override
	public void visitInstanceOf(InstanceOf expression) {
		visit(expression);
	}

	@Override
	public void visitAndAlso(AndAlso expression) {
		visit(expression);
	}

	@Override
	public void visitOrElse(OrElse expression) {
		visit(expression);
	}

	@Override
	public void visitGreaterThan(GreaterThan expression) {
		visit(expression);
	}

	@Override
	public void visitGreaterThanOrEqual(GreaterThanOrEqual expression) {
		visit(expression);
	}

	@Override
	public void visitLessThan(LessThan expression) {
		visit(expression);
	}

	@Override
	public void visitLessThanOrEqual(LessThanOrEqual expression) {
		visit(expression);
	}

	@Override
	public void visitEqual(Equal expression) {
		visit(expression);
	}

	@Override
	public void visitNotEqual(NotEqual expression) {
		visit(expression);
	}

	@Override
	public void visitDevide(Devide expression) {
		visit(expression);
	}

	@Override
	public void visitModulo(Modulo expression) {
		visit(expression);
	}

	@Override
	public void visitMultiply(Multiply expression) {
		visit(expression);
	}

	@Override
	public void visitSubtract(Subtract expression) {
		visit(expression);
	}

	@Override
	public void visitRightShift(RightShift expression) {
		visit(expression);
	}

	@Override
	public void visitCoalesce(Coalesce expression) {
		visit(expression);
	}

	@Override
	public void visitLeftShift(LeftShift expression) {
		visit(expression);
	}

	@Override
	public void visitOr(Or expression) {
		visit(expression);
	}

	@Override
	public void visitXor(Xor expression) {
		visit(expression);
	}

	@Override
	public void visitCast(Cast expression) {
		visit(expression);
	}

	@Override
	public void visitNegate(Negate expression) {
		visit(expression);
	}

	@Override
	public void visitNot(Not expression) {
		visit(expression);
	}

	@Override
	public void visitUnaryPlus(UnaryPlus expression) {
		visit(expression);
	}

	@Override
	public void visitComplement(Complement expression) {
		visit(expression);
	}
}
