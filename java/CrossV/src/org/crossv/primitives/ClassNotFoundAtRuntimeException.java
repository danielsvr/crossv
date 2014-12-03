package org.crossv.primitives;

class ClassNotFoundAtRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -5753640939116698480L;

	public ClassNotFoundAtRuntimeException(ClassNotFoundException cause) {
		super("Class " + cause.getMessage() + " not found", cause);
	}
}
