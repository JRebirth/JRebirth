package org.jrebirth.af.core.command.ref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.core.concurrent.RunInto;
import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.concurrent.RunnablePriority;

public final class GroupRef extends AbstractRef<GroupRef> {
	
	private List<Ref> children = new ArrayList<>();
	
	private boolean sequential;
	
	public static GroupRef create(){
		return new GroupRef();
	}

	public List<Ref> children() {
		return children;
	}
	
	public GroupRef add(Ref... ref) {
		children.addAll(Arrays.asList(ref));
		return this;
	}
	
	public GroupRef remove(Ref... ref) {
		children.removeAll(Arrays.asList(ref));
		return this;
	}
	
	public boolean sequential() {
		return sequential;
	}

	public GroupRef sequential(boolean sequential) {
		this.sequential = sequential;
		return this;
	}

}
