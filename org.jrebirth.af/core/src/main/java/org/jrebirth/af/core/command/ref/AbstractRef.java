package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.concurrent.RunnablePriority;

public abstract class AbstractRef<R extends AbstractRef<R>> implements Ref {

    private RunType runType;
    private RunnablePriority priority;

    public RunType runInto() {
        return this.runType;
    }

    public R runInto(final RunType runType) {
        this.runType = runType;
        return (R) this;
    }

    public RunnablePriority priority() {
        return this.priority;
    }

    public R priority(final RunnablePriority priority) {
        this.priority = priority;
        return (R) this;
    }

}
