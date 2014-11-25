package org.crossv.expressions;

public interface ExpressionVisitor {

	void visit(Expression expression);

	void visitAdd(Add expression);

	void visitAnd(And expression);

	void visitAndAlso(AndAlso expression);

	void visitCall(Call expression);

	void visitCast(Cast expression);

	void visitCoalesce(Coalesce expression);

	void visitConditional(ConditionalTernary expression);

	void visitConstant(Constant expression);

	void visitContext(Context expression);

	void visitDevide(Devide expression);

	void visitEqual(Equal expression);

	void visitGreaterThan(GreaterThan expression);

	void visitGreaterThanOrEqual(GreaterThanOrEqual expression);

	void visitInstance(Instance expression);

	void visitInstanceOf(InstanceOf expression);

	void visitLeftShift(LeftShift expression);

	void visitLessThan(LessThan expression);

	void visitLessThanOrEqual(LessThanOrEqual expression);

	void visitModulo(Modulo expression);

	void visitMultiply(Multiply expression);

	void visitNegate(Negate expression);

	void visitNot(Not expression);

	void visitNotEqual(NotEqual expression);

	void visitOr(Or expression);

	void visitOrElse(OrElse expression);

	void visitRightShift(RightShift expression);

	void visitSubtract(Subtract expression);

	void visitUnaryPlus(UnaryPlus expression);

	void visitXor(Xor expression);

	void visitComplement(Complement expression);

	void visitSequenceLength(SequenceLength expression);

	void visitSequenceIndex(SequenceIndex expression);

	void visitMemberAccess(MemberAccess expression);

	void visitValidIf(ValidIf expression);

	void visitWarnIf(WarnIf expression);

	void visitEvaluation(When expression);
}
