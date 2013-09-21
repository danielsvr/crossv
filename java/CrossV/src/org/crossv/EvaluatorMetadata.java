package org.crossv;

import org.crossv.primitives.ArgumentNullException;

public final class EvaluatorMetadata {
	private String contextName;
	private Class<?> objClass;
	private Class<?> contextClass;

	public EvaluatorMetadata(Class<?> objClass) {
		this(objClass, NoContext.class);
	}

	public EvaluatorMetadata(Class<?> objClass, String contextName) {
		this(objClass, new NamedContext(contextName));
	}

	public EvaluatorMetadata(Class<?> objClass, NamedContext context) {
		this(objClass, NamedContext.class);
		if (context == null)
			throw new ArgumentNullException("context");
		this.contextName = context.getName();
	}

	public EvaluatorMetadata(Class<?> objClass, Class<?> contextClass) {
		if (objClass == null)
			throw new ArgumentNullException("objClass");
		if (contextClass == null)
			throw new ArgumentNullException("contextClass");
		this.objClass = objClass;
		this.contextClass = contextClass;
		this.contextName = contextClass.getName();
	}

	public String getContextName() {
		return contextName;
	}

	public Class<?> getObjClass() {
		return objClass;
	}

	public Class<?> getContextClass() {
		return contextClass;
	}
}
