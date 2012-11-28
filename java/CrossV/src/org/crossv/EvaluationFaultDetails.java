package org.crossv;

public class EvaluationFaultDetails extends EvaluationDetails {

	private Throwable cause;

	public EvaluationFaultDetails(String message) {
		super(message);
	}

	public EvaluationFaultDetails(Throwable cause) {
		super(cause.getMessage());
		this.cause = cause;
	}

	public Throwable getCause(){
		return cause;
	}
}
