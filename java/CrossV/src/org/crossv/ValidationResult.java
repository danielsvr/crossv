package org.crossv;

import org.crossv.primitives.ArgumentNullException;

public class ValidationResult {

	private final Iterable<EvaluationResult> results;
	private boolean isSuccessful;

	public ValidationResult(Iterable<EvaluationResult> results) {
		if (results == null)
			throw new ArgumentNullException("results");
		this.results = results;
		isSuccessful = !EvaluationResults.anyError(results);
	}

	public boolean isSuccessful() {
		return this.isSuccessful;
	}

	public Iterable<EvaluationResult> getResults() {
		return results;
	}
}
