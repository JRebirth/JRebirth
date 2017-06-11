/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
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
package org.jrebirth.af.api.annotation.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation <strong>Bean</strong> used to generate bean implementation code.
 *
 * @author Sébastien Bordes
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Bean {

    /**
     * The name of the class name generated TODO To complete.
     *
     * @return
     */
    String value() default "";

    GeneratorKind kind() default GeneratorKind.None;

    Visibility visibility() default Visibility.public$;

    boolean onlyProperty() default false;

    String propertyPrefix() default "p";

    String getterPrefix() default "";

    String setterPrefix() default "";

    boolean fluent() default true;
}
