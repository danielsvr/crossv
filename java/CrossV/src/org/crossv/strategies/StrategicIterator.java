package org.crossv.strategies;

import static org.crossv.Evaluation.containsAnyFault;
import static org.crossv.primitives.Iterables.addAllToList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.IteratorAdapter;

public abstract class StrategicIterator extends IteratorAdapter<Evaluator>
		implements EvaluatorProxyListener {

	private Iterator<? extends Evaluator> mainIterator;
	private List<Evaluation> evaluations;
	private int iterationDepth;
	private int evaluationDepth;
	private boolean shouldStop;
	private UUID currentBatchId;

	protected StrategicIterator(Iterator<? extends Evaluator> evaluators) {
		if (evaluators == null)
			throw new ArgumentNullException("evaluators");
		mainIterator = evaluators;
		evaluations = new ArrayList<Evaluation>();
		iterationDepth = 1;
		evaluationDepth = 1;
		currentBatchId = UUID.randomUUID();
	}


	@Override
	public final Evaluator next() {
		if (isIterationStopped()) {
			String message = "The iteration stopped due to evaluation faults.";
			throw new NoSuchElementException(message);
		}

		EvaluatorProxy proxy;

		proxy = getNextEvaluator();
		if (proxy == null) {
			iterationDepth++;
			ensureIterationDepth();
			proxy = getNextEvaluator();
		}

		if (proxy == null) {
			String message = "There are no more evaluators to iterate.";
			throw new NoSuchElementException(message);
		}
		return proxy;
	}

	protected void ensureIterationDepth() {
	}

	protected final EvaluatorProxy createProxy(Evaluator realEvaluator) {
		EvaluatorProxy proxy;

		proxy = new EvaluatorProxy(realEvaluator, currentBatchId);
		proxy.addListener(this);
		return proxy;
	}

	@Override
	public final boolean hasNext() {
		if (isIterationStopped())
			return false;

		boolean hasNext = hasNextEvaluator();
		if (!hasNext) {
			iterationDepth++;
			ensureIterationDepth();
			hasNext = hasNextEvaluator();
		}
		return hasNext;
	}

	protected final Iterable<Evaluation> getEvaluations() {
		return evaluations;
	}

	protected final boolean shouldStop() {
		return shouldStop;
	}

	protected final int getIterationDepth() {
		return iterationDepth;
	}

	protected final Iterator<? extends Evaluator> getMainIterator() {
		return mainIterator;
	}

	@Override
	public void evaluateMethodCalled(EvaluatorProxy proxy,
			Iterable<Evaluation> result) {
		if (!proxy.getBatchId().equals(currentBatchId))
			return;
		int contextDepth = proxy.getContextDepth();

		if (contextDepth > evaluationDepth) {
			shouldStop = containsAnyFault(evaluations);
			changingEvaluationDepth();
			evaluationDepth = contextDepth;
		}

		if (isIterationStopped())
			proxy.shouldReturnEmptyResults();
		else
			addAllToList(evaluations, result);
	}

	protected abstract EvaluatorProxy getNextEvaluator();

	protected void changingEvaluationDepth() {
	}

	protected boolean isIterationStopped() {
		return shouldStop;
	}

	protected abstract boolean hasNextEvaluator();
}
