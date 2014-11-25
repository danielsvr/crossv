package org.crossv.expressions;

import java.lang.reflect.AccessibleObject;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ClassDescriptor;
import org.crossv.primitives.MemberDescriptor;

public class MemberAccess extends Expression {
	private Expression instance;
	private MemberDescriptor member;
	private Class<?> resultClass;

	public MemberAccess(Expression instance, AccessibleObject member) {
		init(instance, new MemberDescriptor(member));
	}

	public MemberAccess(Expression instance, MemberDescriptor member) {
		init(instance, member);
	}

	public MemberAccess(Expression instance, String memberName) {
		Class<?> instanceResultClass = instance.getResultClass();
		MemberDescriptor member = findMember(instanceResultClass, memberName);
		if (member == null && instance.isKnownOnlyAtRuntime())
			member = new RuntimeMember(instance, memberName);
		init(instance, member);
	}

	private void init(Expression instance, MemberDescriptor member) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (member == null)
			throw new ArgumentNullException("member");
		this.instance = instance;
		this.member = member;
		verifyOperands();
		resultClass = member.getMemberClass();
	}

	private static MemberDescriptor findMember(Class<?> instanceClass,
			String member) {
		ClassDescriptor descriptor;
		descriptor = new ClassDescriptor(instanceClass);

		return descriptor.findMember(member);
	}

	private void verifyOperands() {
		Class<?> memberDeclaringClass = null;
		Class<?> memberReturnClass = null;

		memberDeclaringClass = member.getDeclaringClass();
		memberReturnClass = member.getMemberClass();

		if (member.isMethod() && member.getParametersCount() > 0)
			throw illegalOperand();

		Class<?> instaceClass = instance.getResultClass();
		if (!memberDeclaringClass.isAssignableFrom(instaceClass)
				|| memberReturnClass.equals(Void.TYPE))
			throw illegalOperand();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	public Expression getInstance() {
		return instance;
	}

	public MemberDescriptor getMember() {
		return member;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitMemberAccess(this);
	}
}
