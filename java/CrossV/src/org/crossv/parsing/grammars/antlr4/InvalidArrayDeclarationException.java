package org.crossv.parsing.grammars.antlr4;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.NotNull;

public class InvalidArrayDeclarationException extends NoViableAltException {
	private static final long serialVersionUID = -3957957509491513254L;
	private String message;

	public InvalidArrayDeclarationException(@NotNull Parser recognizer) {
		this(recognizer, "Invalid array declatation.");
	}

	public InvalidArrayDeclarationException(@NotNull Parser recognizer,
			String message) {
		super(recognizer);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
