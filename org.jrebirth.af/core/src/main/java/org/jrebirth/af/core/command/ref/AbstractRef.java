package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.concurrent.RunnablePriority;

public abstract class AbstractRef<R extends AbstractRef<R>> implements Ref {

	private RunType runType;
	private RunnablePriority priority;
	
	public RunType runType() {
		return runType;
	}
	
	public R runType(RunType runType) {
		this.runType = runType;
		return (R)this;
	}
	
	public RunnablePriority priority() {
		return priority;
	}
	
	public R priority(RunnablePriority priority) {
		this.priority = priority;
		return (R)this;
	}
	
	

}
