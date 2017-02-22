/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.tooling.codegen.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Class extends Packageable<Class> {

    private boolean isInterface = false;

    private boolean isAbstract = false;

    private Packageable<?> superType;

    private List<Packageable<?>> implementedTypes = new ArrayList<>();

    private List<Property> properties = new ArrayList<>();

    private List<Operation> operations = new ArrayList<>();

    public static Class of(final String qualifiedName) {
        return new Class().qualifiedName(qualifiedName);
    }

    public Packageable<?> getSuperType() {
        return this.superType;
    }

    public Class setSuperType(final Packageable<?> superType) {
        this.superType = superType;
        return this;
    }

    public List<Operation> operations() {
        return this.operations;
    }

    public Class operations(final List<Operation> operations) {
        this.operations = operations;
        return this;
    }

    public Class addOperation(final Operation operations) {
        this.operations.addAll(Arrays.asList(operations));
        return this;
    }

    public Class removeOperation(final Operation... operations) {
        this.operations.removeAll(Arrays.asList(operations));
        return this;
    }

    public boolean isInterface() {
        return this.isInterface;
    }

    public Class isInterface(final boolean isInterface) {
        this.isInterface = isInterface;
        return this;
    }

    public boolean isAbstract() {
        return this.isAbstract;
    }

    public Class isAbstract(final boolean isAbstract) {
        this.isAbstract = isAbstract;
        return this;
    }

    public List<Packageable<?>> implementedTypes() {
        return this.implementedTypes;
    }

    public Class implementedTypes(final List<Packageable<?>> implementedTypes) {
        this.implementedTypes = implementedTypes;
        return this;
    }

    public Class addImplementedType(final Packageable<?> implementedTypes) {
        this.implementedTypes.addAll(Arrays.asList(implementedTypes));
        return this;
    }

    public Class removeImplementedType(final Packageable<?>... implementedTypes) {
        this.implementedTypes.removeAll(Arrays.asList(implementedTypes));
        return this;
    }

    public List<Property> properties() {
        return this.properties;
    }

    public Class properties(final List<Property> properties) {
        this.properties = properties;
        return this;
    }

    public Class addProperty(final Property... properties) {
        this.properties.addAll(Arrays.asList(properties));
        return this;
    }

    public Class removeProperty(final Property... properties) {
        this.properties.removeAll(Arrays.asList(properties));
        return this;
    }

}
