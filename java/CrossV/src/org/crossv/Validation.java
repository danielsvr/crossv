package org.crossv;

import org.crossv.primitives.ArgumentNullException;

public class Validation {

	private final Iterable<Evaluation> results;
	private boolean isSuccessful;

	public Validation(Iterable<Evaluation> results) {
		if (results == null)
			throw new ArgumentNullException("results");
		
		this.results = results;
		isSuccessful = !EvaluationResults.anyError(results);
	}

	public boolean isSuccessful() {
		return this.isSuccessful;
	}

	public Iterable<Evaluation> getResults() {
		return results;
	}
	
}
