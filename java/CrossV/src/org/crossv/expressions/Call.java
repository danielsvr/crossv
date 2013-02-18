package org.crossv.expressions;

import java.lang.reflect.Method;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ClassDescriptor;

public class Call extends Expression {
	private Expression instance;
	private Method method;
	private Expression[] parameters;

	public Call(Expression instance, String method, Expression... parameters)
			throws SecurityException, NoSuchMethodException {
		this(instance, findMethod(instance, method, parameters), parameters);
	}

	public Call(Expression instance, Method method, Expression... parameters) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (method == null)
			throw new ArgumentNullException("method");
		Class<?> declaringClass;
		Class<?> resultClass;

		declaringClass = method.getDeclaringClass();
		resultClass = instance.getResultClass();

		if (!declaringClass.isAssignableFrom(resultClass))
			throw new IllegalOperandException();
		if (method.getReturnType().equals(Void.class)
				|| method.getReturnType().equals(Void.TYPE))
			throw new IllegalOperandException();

		if (parameters == null)
			parameters = new Expression[0];
		this.instance = instance;
		this.method = method;
		this.parameters = parameters;
	}

	private static Method findMethod(Expression instance, String method,
			Expression[] parameters) throws SecurityException,
			NoSuchMethodException {
		ClassDescriptor descriptor;
		Class<?>[] paramTypes;
		Class<?> instanceClass = instance.getResultClass();
		descriptor = new ClassDescriptor(instanceClass);

		paramTypes = new Class<?>[parameters.length];
		for (int i = 0; i < parameters.length; i++)
			paramTypes[i] = parameters[i].getResultClass();

		return descriptor.findBestOverload(method, paramTypes);
	}

	public Expression getInstance() {
		return instance;
	}

	public Method getMethod() {
		return method;
	}

	public Expression[] getParameters() {
		return parameters;
	}

	@Override
	public Class<?> getResultClass() {
		return method.getReturnType();
	}
}
