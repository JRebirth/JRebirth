/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.af.api.concurrent;

/**
 * The Enum RunnablePriority used to priorize all runnable.
 */
public enum RunnablePriority {

    /** The Ultimate. */
    Ultimate(7),

    /** The Highest. */
    Highest(7),

    /** The Higher. */
    Higher(6),

    /** The High. */
    High(5),

    /** The Normal. */
    Normal(4),

    /** The Low. */
    Low(3),

    /** The Lower. */
    Lower(2),

    /** The Lowest. */
    Lowest(1),

    /** The None. */
    None(0);

    /** The level. */
    private final int level;

    /**
     * Instantiates a new runnable priority.
     *
     * @param level the level
     */
    private RunnablePriority(final int level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }
}
