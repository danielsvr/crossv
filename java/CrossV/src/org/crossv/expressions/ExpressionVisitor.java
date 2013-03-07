package org.crossv.expressions;

public interface ExpressionVisitor {

	void visit(Expression expression);

	void visitContext(Context expression);

	void visitInstance(Instance expression);

	void visitConstant(Constant expression);

	void visitCall(Call expression);

	void visitConditional(Conditional expression);

	void visitAdd(Add expression);

	void visitAnd(And expression);

	void visitInstanceOf(InstanceOf expression);

	void visitAndAlso(AndAlso expression);

	void visitOrElse(OrElse expression);

	void visitGreaterThan(GreaterThan expression);

	void visitGreaterThanOrEqual(GreaterThanOrEqual expression);

	void visitLessThan(LessThan expression);

	void visitLessThanOrEqual(LessThanOrEqual expression);

	void visitEqual(Equal expression);

	void visitNotEqual(NotEqual expression);

	void visitDevide(Devide expression);

	void visitModulo(Modulo expression);

	void visitMultiply(Multiply expression);

	void visitSubtract(Subtract expression);

	void visitRightShift(RightShift expression);

	void visitCoalesce(Coalesce expression);

	void visitLeftShift(LeftShift expression);

	void visitOr(Or expression);

	void visitXor(Xor expression);

	void visitCast(Cast expression);

	void visitNegate(Negate expression);

	void visitNot(Not expression);

	void visitUnaryPlus(UnaryPlus expression);
}
