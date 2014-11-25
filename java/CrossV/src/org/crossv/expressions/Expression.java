package org.crossv.expressions;

import static org.crossv.expressions.ExpressionEvaluator.NO_CONTEXT;
import static org.crossv.expressions.ExpressionEvaluator.NO_INSTANCE;
import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CCharacter;
import static org.crossv.primitives.ClassDescriptor.CNumber;
import static org.crossv.primitives.ClassDescriptor.TVoid;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class Expression {

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
		evaluator = new ExpressionEvaluator(instance, context);
		return evaluateWith(evaluator);
	}

	public <E> E evaluateWith(ExpressionEvaluator evaluator)
			throws EvaluationException {
		evaluator.evaluate(this);
		return evaluator.getValue();
	}

	public abstract Class<?> getResultClass();

	protected boolean isNullConstant() {
		return false;
	}

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
}