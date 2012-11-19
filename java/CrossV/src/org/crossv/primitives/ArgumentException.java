package org.crossv.primitives;

/**
 * This is an helper exception specific to null arguments
 */
public class ArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = -7332722930057619897L;

	/**
	 * Creates an {@link ArgumentException} with a parameter name.
	 * 
	 * @param argName
	 *            : the name of the parameter
	 */
	public ArgumentException(String argName) {
		this(argName, null);
	}

	/**
	 * Creates an {@link ArgumentException} with a parameter name and a
	 * descriptive message.
	 * 
	 * @param argName
	 *            : the name of the parameter
	 * @param message
	 *            : a descriptive message that explains why the parameter is not
	 *            valid.
	 */
	public ArgumentException(String argName, String message) {
		this(argName, message, true);
	}

	protected ArgumentException(String argName, String message,
			boolean formatMessage) {
		super(formatMessage ? createMessage(argName, message) : message);
	}

	private static String createMessage(String argName, String message) {
		message = message == null ? "" : String.format("\n%s", message);
		argName = argName == null ? "" : argName;
		return String.format("%s parameter is not valid.%s", argName, message);
	}

}
