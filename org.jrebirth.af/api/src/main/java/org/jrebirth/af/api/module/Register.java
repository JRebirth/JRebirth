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
package org.jrebirth.af.api.module;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jrebirth.af.api.annotation.PriorityLevel;

/**
 * The Interface Register.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Register {

    /**
     * Value.
     *
     * @return the class
     */
    Class<?> value() default Class.class;

    /**
     * Priority.
     *
     * @return the registration priority
     */
    PriorityLevel priority() default PriorityLevel.None;

    /**
     * Additional Weight to perform priority calculation.
     * 
     * Shall be strictly lower than 1000
     *
     * @return the registration priority
     */
    int weight() default 0;

}
