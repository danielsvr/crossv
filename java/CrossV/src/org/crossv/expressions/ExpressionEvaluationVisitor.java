package org.crossv.expressions;

final class ExpressionEvaluationVisitor extends ExpressionVisitorAdapter {
	private final ExpressionEvaluator evaluator;

	public ExpressionEvaluationVisitor(ExpressionEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public void visit(Expression expression) {
		evaluator.evaluateUnrecognizedExpresison(expression);
	}

	@Override
	public void visitAdd(Add expression) {
		evaluator.evaluateAdd(expression);
	}

	@Override
	public void visitAnd(And expression) {
		evaluator.evaluateAnd(expression);
	}

	@Override
	public void visitAndAlso(AndAlso expression) {
		evaluator.evaluateAndAlso(expression);
	}

	@Override
	public void visitCall(Call expression) {
		evaluator.evaluateCall(expression);
	}

	@Override
	public void visitCast(Cast expression) {
		evaluator.evaluateCast(expression);
	}

	@Override
	public void visitCoalesce(Coalesce expression) {
		evaluator.evaluateCoalesce(expression);
	}

	@Override
	public void visitConditional(Conditional expression) {
		evaluator.evaluateConditional(expression);
	}

	@Override
	public void visitConstant(Constant expression) {
		evaluator.evaluateConstant(expression);
	}

	@Override
	public void visitContext(Context expression) {
		evaluator.evaluateContext(expression);
	}

	@Override
	public void visitDevide(Devide expression) {
		evaluator.evaluateDevide(expression);
	}

	@Override
	public void visitEqual(Equal expression) {
		evaluator.evaluateEqual(expression);
	}

	@Override
	public void visitGreaterThan(GreaterThan expression) {
		evaluator.evaluateGreaterThan(expression);
	}

	@Override
	public void visitGreaterThanOrEqual(GreaterThanOrEqual expression) {
		evaluator.evaluateGreaterThanOrEqual(expression);
	}

	@Override
	public void visitInstance(Instance expression) {
		evaluator.evaluateInstance(expression);
	}
	
	@Override
	public void visitInstanceOf(InstanceOf expression) {
		evaluator.evaluateInstanceOf(expression);
	}
	
	@Override
	public void visitNotEqual(NotEqual expression) {
		evaluator.evaluateNotEqual(expression);
	}

	@Override
	public void visitOrElse(OrElse expression) {
		evaluator.evaluateOrElse(expression);
	}

	@Override
	public void visitLessThan(LessThan expression) {
		evaluator.evaluateLessThan(expression);
	}

	@Override
	public void visitLessThanOrEqual(LessThanOrEqual expression) {
		evaluator.evaluateLessThanOrEqual(expression);
	}

	@Override
	public void visitModulo(Modulo expression) {
		evaluator.evaluateModulo(expression);
	}

	@Override
	public void visitMultiply(Multiply expression) {
		evaluator.evaluateMultiply(expression);
	}

	@Override
	public void visitSubtract(Subtract expression) {
		// TODO Auto-generated method stub
		super.visitSubtract(expression);
	}

	@Override
	public void visitRightShift(RightShift expression) {
		// TODO Auto-generated method stub
		super.visitRightShift(expression);
	}

	@Override
	public void visitLeftShift(LeftShift expression) {
		evaluator.evaluateLeftShift(expression);
	}

	@Override
	public void visitOr(Or expression) {
		// TODO Auto-generated method stub
		super.visitOr(expression);
	}

	@Override
	public void visitXor(Xor expression) {
		// TODO Auto-generated method stub
		super.visitXor(expression);
	}

	@Override
	public void visitNegate(Negate expression) {
		evaluator.evaluateNegate(expression);
	}

	@Override
	public void visitNot(Not expression) {
		// TODO Auto-generated method stub
		super.visitNot(expression);
	}

	@Override
	public void visitUnaryPlus(UnaryPlus expression) {
		// TODO Auto-generated method stub
		super.visitUnaryPlus(expression);
	}	
}
