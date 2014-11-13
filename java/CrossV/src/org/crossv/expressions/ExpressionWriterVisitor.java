package org.crossv.expressions;

class ExpressionWriterVisitor extends ExpressionVisitorAdapter {
	@Override
	public void visitCast(Cast expression) {
		printer.printCast(expression);
	}

	@Override
	public void visitNegate(Negate expression) {
		printer.printNegate(expression);
	}

	@Override
	public void visitNot(Not expression) {
		printer.printNot(expression);
	}

	@Override
	public void visitUnaryPlus(UnaryPlus expression) {
		printer.printUnaryPlus(expression);
	}

	@Override
	public void visitAdd(Add expression) {
		printer.printAdd(expression);
	}

	@Override
	public void visitAnd(And expression) {
		printer.printAnd(expression);
	}

	@Override
	public void visitInstanceOf(InstanceOf expression) {
		printer.printInstanceOf(expression);
	}

	@Override
	public void visitAndAlso(AndAlso expression) {
		printer.printAndAlso(expression);
	}

	@Override
	public void visitOrElse(OrElse expression) {
		printer.printOrElse(expression);
	}

	@Override
	public void visitGreaterThan(GreaterThan expression) {
		printer.printGreaterThan(expression);
	}

	@Override
	public void visitGreaterThanOrEqual(GreaterThanOrEqual expression) {
		printer.printGreaterThanOrEqual(expression);
	}

	@Override
	public void visitLessThan(LessThan expression) {
		printer.printLessThan(expression);
	}

	@Override
	public void visitLessThanOrEqual(LessThanOrEqual expression) {
		printer.printLessThanOrEqual(expression);
	}

	@Override
	public void visitEqual(Equal expression) {
		printer.printEqual(expression);
	}

	@Override
	public void visitNotEqual(NotEqual expression) {
		printer.printNotEqual(expression);
	}

	@Override
	public void visitDevide(Devide expression) {
		printer.printDevide(expression);
	}

	@Override
	public void visitModulo(Modulo expression) {
		printer.printModulo(expression);
	}

	@Override
	public void visitMultiply(Multiply expression) {
		printer.printMultiply(expression);
	}

	@Override
	public void visitSubtract(Subtract expression) {
		printer.printSubtract(expression);
	}

	@Override
	public void visitRightShift(RightShift expression) {
		printer.printRightShift(expression);
	}

	@Override
	public void visitCoalesce(Coalesce expression) {
		printer.printCoalesce(expression);
	}

	@Override
	public void visitLeftShift(LeftShift expression) {
		printer.printLeftShift(expression);
	}

	@Override
	public void visitOr(Or expression) {
		printer.printOr(expression);
	}

	@Override
	public void visitXor(Xor expression) {
		printer.printXor(expression);
	}

	private ExpressionWriter printer;

	public ExpressionWriterVisitor(ExpressionWriter printer) {
		this.printer = printer;
	}

	@Override
	public void visit(Expression expression) {
		printer.printError(expression);
	}

	@Override
	public void visitContext(Context expression) {
		printer.printContext(expression);
	}

	@Override
	public void visitInstance(Instance expression) {
		printer.printInstance(expression);
	}

	@Override
	public void visitConstant(Constant expression) {
		printer.printConstant(expression);
	}

	@Override
	public void visitCall(Call expression) {
		printer.printCall(expression);
	}

	@Override
	public void visitConditional(ConditionalTernary expression) {
		printer.printConditional(expression);
	}

	@Override
	public void visitComplement(Complement expression) {
		printer.printComplement(expression);
	}
	
	@Override
	public void visitSequenceLength(SequenceLength expression) {
		printer.printSequenceLength(expression);
	}
	
	@Override
	public void visitSequenceIndex(SequenceIndex expression) {
		printer.printSequenceIndex(expression);
	}
	
	@Override
	public void visitMemberAccess(MemberAccess expression) {
		printer.printMemberAccess(expression);
	}
	
	@Override
	public void visitValidIf(ValidIf expression) {
		printer.printValidIf(expression);
	}
	
	@Override
	public void visitWarnIf(WarnIf expression) {
		printer.printWarnIf(expression);
	}
}
