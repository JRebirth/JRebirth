package org.jrebirth.af.rest.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RestTarget {

    enum Protocol {
        http, https
    }

    String value();

    Protocol protocol() default Protocol.http;

    String address() default "";

    int port() default 80;

    String path() default "";

}
