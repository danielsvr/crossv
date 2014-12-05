package org.crossv.expressions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ClassDescriptor;
import org.crossv.primitives.MemberDescriptor;
import org.crossv.primitives.RuntimeMethod;

public class Call extends Expression {
	private Expression instance;
	private MemberDescriptor member;
	private Iterable<Expression> parameters;

	public Call(Expression instance, String method,
			Iterable<Expression> parameters) {
		MemberDescriptor member;
		if (instance.isKnownOnlyAtRuntime()) {
			member = new RuntimeMethod(instance, method);
		} else {
			Method foundMethod = findMethod(instance, method, parameters);
			member = new MemberDescriptor(foundMethod);
		}

		init(instance, member, parameters);
	}

	public Call(Expression instance, Method method,
			Iterable<Expression> parameters) {
		init(instance, new MemberDescriptor(method), parameters);
	}

	private void init(Expression instance, MemberDescriptor method,
			Iterable<Expression> parameters) {
		if (instance == null)
			throw new ArgumentNullException("instance");

		this.instance = instance;
		this.member = method;
		this.parameters = parameters;

		verifyOperands();
	}

	private void verifyOperands() {
		if (instance instanceof Instance || instance instanceof Context)
			return;

		Class<?> declaringClass = member.getDeclaringClass();
		Class<?> resultClass = instance.getResultClass();

		if (!declaringClass.isAssignableFrom(resultClass)
				|| member.getMemberClass().equals(Void.TYPE))
			throw illegalOperand();
	}

	private static Method findMethod(Expression instance, String method,
			Iterable<Expression> parameters) {
		ClassDescriptor descriptor;
		List<Class<?>> paramTypes;
		Class<?> instanceClass = instance.getResultClass();
		descriptor = new ClassDescriptor(instanceClass);

		paramTypes = new ArrayList<Class<?>>();
		for (Expression param : parameters)
			paramTypes.add(param.getResultClass());

		try {
			return descriptor.findBestOverload(method, paramTypes);
		} catch (NoSuchMethodException e) {
			throw new RuntimeEvaluationException(e);
		}
	}

	public Expression getInstance() {
		return instance;
	}

	public MemberDescriptor getMethod() {
		return member;
	}

	public Iterable<Expression> getParameters() {
		return parameters;
	}

	@Override
	public Class<?> getResultClass() {
		return member.getMemberClass();
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitCall(this);
	}

	public String getMethodName() {
		return member.getName();
	}
}
