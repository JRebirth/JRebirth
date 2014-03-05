package org.jrebirth.af.core.annotation;

/**
 * The class <strong>MethodPriority</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum MethodPriority {

    /** The lowest priority, method will be run at the end. */
    LOWEST,

    /** The lowest priority, method will be run after normal methods but before lowest methods. */
    LOW,

    /** The normal priority, method will be run before lower methods but after high methods. */
    NORMAL,

    /** The high priority, method will be run just after Highest methods. */
    HIGH,

    /** The highest priority, method will be run at the beginning. */
    HIGHEST

}
