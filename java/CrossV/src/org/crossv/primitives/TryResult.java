package org.crossv.primitives;

public class TryResult<E> {
	private boolean success;
	private E result;
	private Exception cause;

	public TryResult(Exception cause) {
		this.cause = cause;
		this.success = false;
	}
	
	public TryResult() {
		this.success = false;
	}

	public TryResult(E result) {
		this.success = true;
		this.result = result;
	}

	public boolean isSuccessful() {
		return success;
	}

	public E getResult() {
		return result;
	}

	public Exception getCause() {
		return cause;
	}
}
