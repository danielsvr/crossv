package org.crossv.primitives;

/**
 * This is an helper exception specific to null arguments
 * 
 * @author OG USER
 */
public class ArgumentNullException extends IllegalArgumentException {
	private static final long serialVersionUID = -7332722930057619897L;

	/**
	 * Creates an {@link ArgumentNullException} with a parameter name.
	 * 
	 * @param argName
	 *            : the name of the parameter
	 */
	public ArgumentNullException(String argName) {
		this(argName, null);
	}

	/**
	 * Creates an {@link ArgumentNullException} with a parameter name and a
	 * descriptive message.
	 * 
	 * @param argName
	 *            : the name of the parameter
	 * @param message
	 *            : a descriptive message that explains why the parameter must
	 *            not be null.
	 */
	public ArgumentNullException(String argName, String message) {
		super(createMessage(argName, message));
	}

	private static String createMessage(String argName, String message) {
		message = message == null ? "" : String.format("\n%s", message);
		argName = argName == null ? "" : argName;
		return String.format("%s parameter is null.%s", argName, message);
	}
	
}
