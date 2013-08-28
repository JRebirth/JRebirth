package org.jrebirth.core.resource;

public class ParameterEntry<O extends Object> {

    private final String serializedString;

    private O object;

    public ParameterEntry(final String serializedString) {
        super();
        this.serializedString = serializedString;
    }

    public ParameterEntry(final String string, final O object2) {
        this(string);
        this.object = object2;
    }

    /**
     * @return Returns the serializedString.
     */
    public String getSerializedString() {
        return this.serializedString;
    }

    /**
     * @return Returns the object.
     */
    public O getObject() {
        return this.object;
    }

}
