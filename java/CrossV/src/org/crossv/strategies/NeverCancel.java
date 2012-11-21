package org.crossv.strategies;

public class NeverCancel implements IteratorCancelationSource {
	public static NeverCancel instance = new NeverCancel();

	private NeverCancel() {
	}

	public final boolean isCanceled() {
		return false;
	}
}
