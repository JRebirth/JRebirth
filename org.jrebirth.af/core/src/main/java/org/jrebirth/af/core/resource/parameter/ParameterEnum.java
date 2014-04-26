package org.jrebirth.af.core.resource.parameter;

public interface ParameterEnum extends ParameterItem {

    /**
     * Default Constructor.
     * 
     * @param parameterName the name of the parameter
     * @param object the parameter object
     */
    default <O> void param(final String parameterName, final O object) {
        set(new ObjectParameter<O>(parameterName, object));
    }

}