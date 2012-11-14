package org.crossv.tests.subjects;

import org.crossv.Evaluation;
import org.crossv.Evaluator;

public interface TestableEvaluator extends Evaluator {
	void returns(Evaluation... results);
}
