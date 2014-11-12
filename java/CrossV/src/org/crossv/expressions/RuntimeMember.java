package org.crossv.expressions;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.CObject;

import java.lang.reflect.InvocationTargetException;

import org.crossv.primitives.ClassDescriptor;
import org.crossv.primitives.MemberDescriptor;

public class RuntimeMember extends MemberDescriptor {
	private String name;
	private Expression instance;

	public RuntimeMember(Expression instance, String name) {
		this.instance = instance;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<?> getDeclaringClass() {
		return CObject;
	}

	@Override
	public Class<?> getMemberClass() {
		return CObject;
	}

	public Expression getInstance() {
		return instance;
	}

	public Object invoke(ExpressionEvaluator instanceEvaluator)
			throws InvocationTargetException, IllegalAccessException,
			IllegalArgumentException {
		try {
			instanceEvaluator.evaluate(instance);
			Object obj = instanceEvaluator.getValue();
			ClassDescriptor desctiptor;
			desctiptor = new ClassDescriptor(obj.getClass());
			MemberDescriptor member = desctiptor.findMember(name);
			if (member == null) {
				String message = "Cannot invoke \"{0}\" member.";
				message = format(message, name);
				throw new IllegalAccessException(message);
			}
			return member.invoke(obj);
		} catch (EvaluationException e) {
			String message = "Cannot invoke \"{0}\" member. Please inspect cause for more details.";
			message = format(message, name);
			throw new InvocationTargetException(e, message);
		}
	}

	@Override
	public Object invoke(Object instance, Object... parameters)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String message = "Cannot invoke \"{0}\" member without an evaluator. Please use the overload.";
		message = format(message, name);
		throw new IllegalAccessException(message);
	}
}
