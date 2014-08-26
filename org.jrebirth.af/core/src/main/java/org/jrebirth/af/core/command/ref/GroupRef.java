package org.jrebirth.af.core.command.ref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GroupRef extends AbstractRef<GroupRef> {

    private final List<Ref> children = new ArrayList<>();

    private boolean sequential;

    public static GroupRef create() {
        return new GroupRef();
    }

    public List<Ref> children() {
        return this.children;
    }

    public GroupRef add(final Ref... ref) {
        this.children.addAll(Arrays.asList(ref));
        return this;
    }

    public GroupRef remove(final Ref... ref) {
        this.children.removeAll(Arrays.asList(ref));
        return this;
    }

    public boolean sequential() {
        return this.sequential;
    }

    public GroupRef sequential(final boolean sequential) {
        this.sequential = sequential;
        return this;
    }

    @Override
    public SingleRef single() {
        // Nothing to do yet
        return null;
    }

    @Override
    public GroupRef group() {
        // Nothing to do yet
        return null;
    }

    @Override
    public RealRef real() {
        // Nothing to do yet
        return null;
    }

}
