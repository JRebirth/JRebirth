package org.jrebirth.af.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jrebirth.af.api.command.MultiCommand;

/**
 * The annotation <strong>Sequential</strong> is only applicable on {@link MultiCommand} commands.
 *
 * @author Sébastien Bordes
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Sequential {

    /**
     * Define if command must be run sequentially or in parallel. <br />
     *
     * - a True value (default) will configure the {@link MultiCommand} to run sub commands sequentially according to order of addition. <br />
     *
     * - a False value will run all command in the same order but without waiting the end of each command
     */
    boolean value() default true;
}
