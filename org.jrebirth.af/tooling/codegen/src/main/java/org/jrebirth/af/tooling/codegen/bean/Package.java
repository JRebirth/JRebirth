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

import java.util.Arrays;
import java.util.List;

public class Package extends Packageable<Package> {

    private List<Packageable<?>> packageables;

    public static Package create() {
        return new Package();
    }

    public Package packageables(final List<Packageable<?>> packageables) {
        this.packageables = packageables;
        return this;
    }

    public List<Packageable<?>> packageables() {
        return this.packageables;
    }

    public Package addPackageable(final Packageable<?>... packageables) {
        this.packageables.addAll(Arrays.asList(packageables));
        return this;
    }

    public Package removePackageable(final Packageable<?>... packageables) {
        this.packageables.removeAll(Arrays.asList(packageables));
        return this;
    }

}
