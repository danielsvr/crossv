package org.crossv;

import static org.crossv.EvaluationUtil.containsAnyFault;
import static org.crossv.primitives.Iterables.select;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.Function;

public class Validation {
	private EvaluationMessageSelector messageOnly;
	private final Iterable<Evaluation> results;
	private boolean isSuccessful;

	public Validation(Iterable<Evaluation> results) {
		if (results == null)
			throw new ArgumentNullException("results");

		this.results = results;
		this.isSuccessful = !containsAnyFault(results);
		this.messageOnly = new EvaluationMessageSelector();
	}

	public boolean isSuccessful() {
		return this.isSuccessful;
	}

	public Iterable<Evaluation> getResults() {
		return results;
	}

	public Iterable<String> getMessages() {
		return select(results, messageOnly);
	}

	private class EvaluationMessageSelector implements
			Function<Evaluation, String> {
		@Override
		public String eval(Evaluation value) {
			return value.getMessage();
		}
	}
}
