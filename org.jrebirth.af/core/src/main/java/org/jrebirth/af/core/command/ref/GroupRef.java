/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.core.command.ref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupRef.
 */
public final class GroupRef extends AbstractRef<GroupRef> {

    /** The children. */
    private final List<Ref> children = new ArrayList<>();

    /** The sequential. */
    private boolean sequential;

    /**
     * Creates the.
     *
     * @return the group ref
     */
    public static GroupRef create() {
        return new GroupRef();
    }

    /**
     * Children.
     *
     * @return the list
     */
    public List<Ref> children() {
        return this.children;
    }

    /**
     * Adds the.
     *
     * @param ref the ref
     * @return the group ref
     */
    public GroupRef add(final Ref... ref) {
        this.children.addAll(Arrays.asList(ref));
        return this;
    }

    /**
     * Removes the.
     *
     * @param ref the ref
     * @return the group ref
     */
    public GroupRef remove(final Ref... ref) {
        this.children.removeAll(Arrays.asList(ref));
        return this;
    }

    /**
     * Sequential.
     *
     * @return true, if successful
     */
    public boolean sequential() {
        return this.sequential;
    }

    /**
     * Sequential.
     *
     * @param sequential the sequential
     * @return the group ref
     */
    public GroupRef sequential(final boolean sequential) {
        this.sequential = sequential;
        return this;
    }

}
