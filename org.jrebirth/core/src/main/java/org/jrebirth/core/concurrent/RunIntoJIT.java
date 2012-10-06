package org.jrebirth.core.concurrent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * *
 * 
 * @author Sébastien Bordes
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface RunIntoJIT {
}
