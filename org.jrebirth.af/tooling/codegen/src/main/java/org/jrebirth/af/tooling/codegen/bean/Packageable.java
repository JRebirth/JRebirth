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

public class Packageable<P extends Packageable<P>> extends Nameable<P> {

    /** The package name. */
    private Package _package;

    /**
     * Gets the full class name.
     *
     * @return Returns the full class name.
     */
    public String qualifiedName() {
        return this._package != null ? this._package.qualifiedName() + "." + name() : name();
    }

    public P qualifiedName(final String qualifiedName) {
        final String[] parts = qualifiedName.split("\\.");
        name(parts[parts.length - 1]);
        if (parts.length > 1) {
            _package(Package.create().qualifiedName(qualifiedName.substring(0, qualifiedName.lastIndexOf("."))));
        }
        return (P) this;
    }

    /**
     * Gets the package name.
     *
     * @return Returns the packageName.
     */
    public Package _package() {
        return this._package;
    }

    /**
     * Sets the package name.
     *
     * @param packageName The packageName to set.
     */
    @SuppressWarnings("unchecked")
    public P _package(final Package _package) {
        this._package = _package;
        return (P) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return qualifiedName();
    }

}
