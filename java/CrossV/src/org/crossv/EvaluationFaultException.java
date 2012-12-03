package org.crossv;

public class EvaluationFaultException extends RuntimeException {
	private static final long serialVersionUID = 5499545251304399363L;
	private final Throwable cause;

	public EvaluationFaultException(String message) {
		super(message);
		this.cause = null;
	}

	public EvaluationFaultException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}

	@Override
	public boolean equals(Object obj) {
		Throwable otherException;
		
		otherException = null;
		if (obj != null && obj instanceof EvaluationFaultException) {
			EvaluationFaultException faultException = (EvaluationFaultException) obj;
			otherException = faultException.cause;
		} else if (obj != null && obj instanceof Throwable) {
			otherException = (Throwable) obj;
		}

		if (otherException != null)
			return cause == null && otherException == null
					|| (cause != null && cause.equals(otherException));

		return super.equals(obj);
	}
}
