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
package org.jrebirth.af.tools.codegen.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class FXBeanDefinition.
 */
public class FXBeanDefinition {

    /** The package name. */
    private String packageName;

    /** The class name. */
    private String className;

    private String superType;

    /** The properties. */
    private List<FXPropertyDefinition> properties = new ArrayList<>();

    /**
     * Gets the full class name.
     *
     * @return Returns the full class name.
     */
    public String getFullClassName() {
        return this.packageName + "." + this.className;
    }

    /**
     * Gets the package name.
     *
     * @return Returns the packageName.
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * Sets the package name.
     *
     * @param packageName The packageName to set.
     */
    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets the class name.
     *
     * @return Returns the className.
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * Sets the class name.
     *
     * @param className The className to set.
     */
    public void setClassName(final String className) {
        this.className = className;
    }

    /**
     * @return Returns the superType.
     */
    public String getSuperType() {
        return superType;
    }

    /**
     * @param superType The superType to set.
     */
    public void setSuperType(String superType) {
        this.superType = superType;
    }

    /**
     * Gets the properties.
     *
     * @return Returns the properties.
     */
    public List<FXPropertyDefinition> getProperties() {
        return this.properties;
    }

    /**
     * Sets the properties.
     *
     * @param properties The properties to set.
     */
    public void setProperties(final List<FXPropertyDefinition> properties) {
        this.properties = properties;
    }

}
