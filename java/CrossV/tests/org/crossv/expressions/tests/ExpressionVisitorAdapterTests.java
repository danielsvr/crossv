package org.crossv.expressions.tests;

import junit.framework.AssertionFailedError;

import org.crossv.expressions.Expression;
import org.crossv.expressions.ExpressionVisitorAdapter;
import org.junit.Test;

public class ExpressionVisitorAdapterTests {
	private static class VisitorMock extends ExpressionVisitorAdapter {
		private boolean visitExpressionCalled;

		@Override
		public void visit(Expression expression) {
			super.visit(expression);
			visitExpressionCalled = true;
		}

		public void assertVisitExpressionCalled() {
			if (!visitExpressionCalled)
				throw new AssertionFailedError("org.crossv.expressions."
						+ "ExpressionVisitorAdapter.visit(Expression) "
						+ "was not called");
		}
	}

	@Test
	public void visitAdd_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitAdd(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitAnd_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitAnd(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitAndAlso_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitAndAlso(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitContext_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitContext(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitInstance_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitInstance(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitConstant_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitConstant(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitCall_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitCall(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitConditional_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitConditional(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitInstanceOf_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitInstanceOf(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitOrElse_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitOrElse(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitGreaterThan_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitGreaterThan(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitGreaterThanOrEqual_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitGreaterThanOrEqual(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitLessThan_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitLessThan(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitLessThanOrEqual_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitLessThanOrEqual(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitEqual_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitEqual(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitNotEqual_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitNotEqual(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitDevide_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitDevide(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitModulo_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitModulo(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitMultiply_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitMultiply(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitSubtract_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitSubtract(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitRightShift_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitRightShift(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitCoalesce_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitCoalesce(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitLeftShift_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitLeftShift(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitOr_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitOr(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitXor_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitXor(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitCast_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitCast(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitNegate_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitNegate(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitNot_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitNot(null);
		visitor.assertVisitExpressionCalled();
	}

	@Test
	public void visitUnaryPlus_AnyExpression_VisitExpressionIsCalled() {
		VisitorMock visitor = new VisitorMock();
		visitor.visitUnaryPlus(null);
		visitor.assertVisitExpressionCalled();
	}
}
