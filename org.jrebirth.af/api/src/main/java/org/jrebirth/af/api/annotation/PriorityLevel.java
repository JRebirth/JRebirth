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
package org.jrebirth.af.api.annotation;

/**
 * The class <strong>PriorityLevel</strong>.
 *
 * @author Sébastien Bordes
 */
public enum PriorityLevel {

    /** The None priority when none has been defined. */
    None(0),

    /** The lowest priority, after lower, is the less important priority level. */
    Lowest(1),

    /** The lower priority, after Low but before Lowest. */
    Lower(2),

    /** The low priority, after Normal but before Lower. */
    Low(3),

    /** The normal priority, after high but before Low. */
    Normal(4),

    /** The High priority, after higher but before Normal. */
    High(5),

    /** The Higher priority, after highest but before high. */
    Higher(6),

    /** The highest priority, after ultimate but before higher. */
    Highest(7),

    /** The Ultimate is the most important priority level. */
    Ultimate(8);

    /** The level. */
    private final int level;

    /**
     * Instantiates a new priority level.
     *
     * @param level the level
     */
    private PriorityLevel(final int level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public int level() {
        return this.level * 1000;
    }

    /**
     * Gets the level be adding an extra weight.
     *
     * @param weight the weight value shall be strictly lower than 1000
     *
     * @return the level
     */
    public int level(final int weight) {
        if (weight < 1000) {
            return this.level * 1000 + weight;
        }
        // Silently ignore weight
        return level();
    }

}
