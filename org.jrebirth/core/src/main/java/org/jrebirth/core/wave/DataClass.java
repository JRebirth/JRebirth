package org.jrebirth.core.wave;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>DataClass</strong>.
 * 
 * @author Sébastien Bordes
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface DataClass {

    /**
     * The class value.
     */
    Class<?> value();
}
