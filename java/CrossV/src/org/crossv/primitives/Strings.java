package org.crossv.primitives;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Strings {
	private Strings() {
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.length() == 0;
	}

	public static boolean isNullOrWhitespace(String value) {
		if (value == null || value.length() == 0)
			return true;
		for (int i = 0; i < value.length(); i++) {
			char chr = value.charAt(i);
			boolean isWhitespace = Character.isWhitespace(chr);
			if (!isWhitespace)
				return false;
		}
		return true;
	}

	public static String getStacktraceOf(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}
}
