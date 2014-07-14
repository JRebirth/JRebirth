package org.jrebirth.af.core.command.ref;

public interface Ref {

    static SingleRef single() {
        return SingleRef.create();
    }

    static GroupRef group() {
        return GroupRef.create();
    }

    static RealRef real() {
        return RealRef.create();
    }

}
