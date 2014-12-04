package org.crossv.expressions;

import static org.crossv.expressions.ExpressionEvaluator.NO_CONTEXT;
import static org.crossv.expressions.ExpressionEvaluator.NO_INSTANCE;
import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CCharacter;
import static org.crossv.primitives.ClassDescriptor.CNumber;
import static org.crossv.primitives.ClassDescriptor.TVoid;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;
import org.crossv.parsing.grammars.antlr4.CrossVLexer;
import org.crossv.parsing.grammars.antlr4.CrossVParser;

public abstract class Expression {

	protected Expression() {
	}

	@Override
	public final String toString() {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		print(writer);
		return stringWriter.toString();
	}

	public void print() {
		PrintWriter writer = new PrintWriter(System.out);
		print(writer);
	}

	public void print(PrintWriter writer) {
		print(new ExpressionWriter(writer));
	}

	public void print(ExpressionWriter writer) {
		writer.print(this);
	}

	public <E> E evaluate() throws EvaluationException {
		return evaluate(NO_INSTANCE);
	}

	public <E> E evaluate(Object instance) throws EvaluationException {
		return evaluate(instance, NO_CONTEXT);
	}

	public <E> E evaluate(Object instance, Object context)
			throws EvaluationException {
		ExpressionEvaluator evaluator;
		instance = instance != null ? instance : NO_INSTANCE;
		EvaluationOptions options = new EvaluationOptions();
		options.printStackCount = true;
		options.printStackValues = true;
		evaluator = new BasicExpressionEvaluator(instance, context, options);
		return evaluateWith(evaluator);
	}

	public <E> E evaluateWith(ExpressionEvaluator evaluator)
			throws EvaluationException {
		evaluator.evaluate(this);
		return evaluator.getValue();
	}

	public abstract Class<?> getResultClass();

	protected boolean isAssignableTo(Class<?> clazz) {
		Class<?> resultClass = getResultClass();
		return clazz.isAssignableFrom(resultClass);
	}

	protected boolean isAssignableToAny(Class<?> clazz1, Class<?> clazz2) {
		if (isAssignableTo(clazz1))
			return true;
		if (isAssignableTo(clazz2))
			return true;
		return false;
	}

	protected boolean isAssignableToAny(Class<?> clazz1, Class<?> clazz2,
			Class<?> clazz3) {
		if (isAssignableToAny(clazz1, clazz2))
			return true;
		if (isAssignableTo(clazz3))
			return true;
		return false;
	}

	protected boolean isAssignableToAny(Class<?> clazz1, Class<?> clazz2,
			Class<?> clazz3, Class<?> clazz4) {
		if (isAssignableToAny(clazz1, clazz2, clazz3))
			return true;
		if (isAssignableTo(clazz4))
			return true;
		return false;
	}

	protected boolean isArray() {
		Class<?> resultClass = getResultClass();
		return resultClass.isArray();
	}

	protected boolean isKnownOnlyAtRuntime() {
		return false;
	}

	protected boolean returnsPrimitiveType() {
		return isAssignableToAny(TVoid, CNumber, CCharacter, CBoolean);
	}

	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}

	protected static IllegalOperandException illegalOperand() {
		return new IllegalOperandException();
	}

	protected static CrossVParser createTextParser(String text) {
		Reader reader = new StringReader(text);
		return createParser(reader, true);
	}

	protected static CrossVParser createParser(Reader reader) {
		return createParser(reader, false);
	}

	protected static CrossVParser createParser(Reader reader,
			boolean useCommonTokenFactory) {
		UnbufferedCharStream inputStream = new UnbufferedCharStream(reader);
		Lexer lexer = new CrossVLexer(inputStream);
		if (useCommonTokenFactory)
			lexer.setTokenFactory(new CommonTokenFactory(true));
		TokenStream tokenStream = new UnbufferedTokenStream<CommonToken>(lexer);
		CrossVParser parser = new CrossVParser(tokenStream);
		parser.removeErrorListeners();
		parser.removeParseListeners();
		parser.setErrorHandler(new ErrorStrategy());
		return parser;
	}

	private static class ErrorStrategy extends DefaultErrorStrategy {
		@Override
		public void recover(Parser recognizer, RecognitionException e) {
			throw new RuntimeException(e);
		}

		@Override
		public Token recoverInline(Parser recognizer)
				throws RecognitionException {
			throw new RuntimeException(new InputMismatchException(recognizer));
		}

		@Override
		public void sync(Parser recognizer) {
		}

		@Override
		public void reportNoViableAlternative(Parser parser,
				NoViableAltException e) throws RecognitionException {
			String msg = e.getMessage();
			parser.notifyErrorListeners(e.getOffendingToken(), msg, e);
		}
	}
}