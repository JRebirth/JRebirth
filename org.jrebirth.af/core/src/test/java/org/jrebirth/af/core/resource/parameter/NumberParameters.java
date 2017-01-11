package org.jrebirth.af.core.resource.parameter;

public enum NumberParameters implements ParameterEnum<Number> {

    // @formatter:off

    number1 {{ param("number1", new Integer(1));}},
    number2 {{ param("number2", new Float(3.333));}},
    number3 {{ param(new Long( 1_000_000_000_000L));}},
    ;


}
