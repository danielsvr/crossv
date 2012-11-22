package org.crossv;

/**
 * Holds all the information needed as details of an evaluation.
 * 
 * @author yochanan.miykael
 */
public class EvaluationDetails {

	private String message;

	/**
	 * Creates an instance of {@link EvaluationDetails}.
	 */
	public EvaluationDetails(String message) {
		this.message = message;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
