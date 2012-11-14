package org.crossv;

public class IllegalObjectException extends IllegalArgumentException {
	private static final long serialVersionUID = -2014799851390392177L;

	public IllegalObjectException(String objectName) {
		this(objectName, null);
	}

	public IllegalObjectException(String objectName, String message) {
		super(createMessage(objectName, message));
	}

	private static String createMessage(String objectName, String message) {
		message = message == null ? "" : String.format("\n%s", message);
		objectName = objectName == null ? "" : objectName;
		return String.format("%s is not a valid object.%s", objectName, message);
	}
}
