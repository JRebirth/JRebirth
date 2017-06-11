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

public class Enum extends Packageable<Enum> {

    private List<String> values = new ArrayList<>();


    public static Enum of(final String qualifiedName) {
        return new Enum().qualifiedName(qualifiedName);
    }
    
    public List<String> values() {
        return this.values;
    }

    public Enum values(final List<String> values) {
        this.values = values;
        return this;
    }

    public Enum addValue(final String values) {
        this.values.addAll(Arrays.asList(values));
        return this;
    }

    public Enum removeValue(final String... values) {
        this.values.removeAll(Arrays.asList(values));
        return this;
    }

}