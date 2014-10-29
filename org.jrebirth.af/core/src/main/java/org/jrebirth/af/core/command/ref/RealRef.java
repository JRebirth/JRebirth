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

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.key.UniqueKey;

// TODO: Auto-generated Javadoc
/**
 * The Class RealRef.
 */
public class RealRef implements Ref {

    /** The command key. */
    private UniqueKey<? extends Command> commandKey;

    /**
     * Creates the.
     *
     * @return the real ref
     */
    public static RealRef create() {
        return new RealRef();
    }

    /**
     * Key.
     *
     * @return the unique key<? extends command>
     */
    public UniqueKey<? extends Command> key() {
        return this.commandKey;
    }

    /**
     * Key.
     *
     * @param commandKey the command key
     * @return the real ref
     */
    public RealRef key(final UniqueKey<? extends Command> commandKey) {
        this.commandKey = commandKey;
        return this;
    }

}
