/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.api.application;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to manage the Messages file service.
 *
 * @author Sébastien Bordes
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Localized {

    /**
     * Define the wildcard used to find Messages files. The format is the same as Regex Pattern (ie: .*-jrebirth => for abcjrebirth.EXTENSION files)
     *
     * The default value is empty, no search will be done
     */
    String value() default "";

    /**
     * Define the delay used to check if the file has changed in order to reload messages files.<br />
     * This value is in seconds.
     *
     * The default value is 0, no check will be done
     */
    long schedule() default 0;
}
