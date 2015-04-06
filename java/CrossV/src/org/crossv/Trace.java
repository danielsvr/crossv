package org.crossv;


public interface Trace {
	void enter(String name, Object... parametes);
	void exit(String name);
	void exit(String name, Object result);
}
