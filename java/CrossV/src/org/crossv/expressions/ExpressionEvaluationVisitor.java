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
	public void visitConstant(Constant expression) {
		evaluator.evaluateConstant(expression);
	}

	@Override
	public void visitContext(Context expression) {
		evaluator.evaluateContext(expression);
	}

	@Override
	public void visitInstance(Instance expression) {
		evaluator.evaluateInstance(expression);
	}

	@Override
	public void visitConditional(Conditional expression) {
		evaluator.evaluateConditional(expression);
	}

	@Override
	public void visitCoalesce(Coalesce expression) {
		evaluator.evaluateCoalesce(expression);
	}

	@Override
	public void visitNotEqual(NotEqual expression) {
		evaluator.evaluateNotEqual(expression);
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
	public void visitInstanceOf(InstanceOf expression) {
		evaluator.evaluateInstanceOf(expression);
	}
}
