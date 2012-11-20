package org.crossv;

/**
 * Utility class for defining evaluators without context.
 * 
 * @author yochanan.miykael
 */
public final class NoContext {

	/**
	 * Holds the singleton instance of {@link NoContext} class.
	 * */
	public final static NoContext instance = new NoContext();

	private NoContext() {
	}
}
