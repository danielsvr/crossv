package org.crossv;

import java.util.Hashtable;

public class SystemOutTrace implements Trace {

	Hashtable<String, Long> times = new Hashtable<String, Long>();
	
	@Override
	public void enter(String name, Object... parametes) {
		times.put(name, System.currentTimeMillis());
		System.out.println("Start executing: " + name);
	}

	@Override
	public void exit(String name) {
		Long startTime = times.get(name);
		times.put(name, System.currentTimeMillis() - startTime);
		System.out.println("finished: " + name + "; elipsed: " + times.get(name));
	}

	@Override
	public void exit(String name, Object result) {
	}

}
