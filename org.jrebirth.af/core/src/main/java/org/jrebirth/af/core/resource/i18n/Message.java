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
package org.jrebirth.af.core.resource.i18n;

import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.core.resource.AbstractBaseParams;

/**
 * The interface <strong>Message</strong>.
 *
 * @author Sébastien Bordes
 */
public class Message extends AbstractBaseParams implements MessageParams {

    /** The message name. */
    private final String messageName;

    /**
     * Default Constructor.
     *
     * @param parameterName the name of the parameter
     */
    public Message(final String parameterName) {
        super();
        this.messageName = parameterName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return this.messageName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] string) {
        // Nothing to do, method added to be compliant with API
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(name());
    }
}
