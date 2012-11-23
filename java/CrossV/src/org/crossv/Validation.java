package org.crossv;

import static org.crossv.EvaluationUtil.containsAnyFault;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.Function;
import org.crossv.primitives.Iterables;

public class Validation {

	private final Iterable<Evaluation> results;
	private boolean isSuccessful;

	public Validation(Iterable<Evaluation> results) {
		if (results == null)
			throw new ArgumentNullException("results");

		this.results = results;
		isSuccessful = !containsAnyFault(results);
	}

	public boolean isSuccessful() {
		return this.isSuccessful;
	}

	public Iterable<Evaluation> getResults() {
		return results;
	}

	public Iterable<String> getMessages() {
		return Iterables.select(results, new Function<Evaluation, String>() {
			public String eval(Evaluation value) {
				return value.getMessage();
			}
		});
	}
}
