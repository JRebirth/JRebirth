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

public class Operation extends Nameable<Operation> {

    private boolean isAbstract = false;

    private boolean isStatic = false;

    private boolean isDefault = false;

    /** The return type. */
    private Class returnType = null;

    private Visibility visibility = Visibility._package;

    private List<Parameter> parameters = new ArrayList<>();

    public static Operation of(final String name) {
        return new Operation().name(name);
    }

    public boolean isAbstract() {
        return this.isAbstract;
    }

    public Operation isAbstract(final boolean isAbstract) {
        this.isAbstract = isAbstract;
        return this;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public Operation isStatic(final boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public Operation isDefault(final boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    public Visibility visibility() {
        return this.visibility;
    }

    public Operation visibility(final Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public Class returnType() {
        return this.returnType;
    }

    public Operation returnType(final Class type) {
        this.returnType = type;
        return this;
    }

    public List<Parameter> parameters() {
        return this.parameters;
    }

    public Operation parameters(final List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Operation addParameter(final Parameter parameters) {
        this.parameters.addAll(Arrays.asList(parameters));
        return this;
    }

    public Operation removeParameter(final Parameter... parameters) {
        this.parameters.removeAll(Arrays.asList(parameters));
        return this;
    }

}
