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
package org.jrebirth.af.core.facade;

import java.util.StringTokenizer;

import org.jrebirth.af.api.facade.JRebirthEvent;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.core.util.ClassUtility;

/**
 *
 * The class <strong>JRebirthEventBase</strong>.
 *
 * This Bean is used to store event data.
 */
public final class JRebirthEventBase implements JRebirthEvent {

    /** The sequence number. */
    private int sequence;

    /** The type of the event. */
    private JRebirthEventType eventType;

    /** The source class. */
    private String source;

    /** The target class. */
    private String target;

    /** The event data. */
    private String eventData;

    /**
     * Default Constructor with mandatory fields.
     *
     * @param sequence the sequence number
     * @param eventType the type of the event
     * @param source the source class of the event
     * @param target the target of the event
     * @param eventData the data of the event
     */
    public JRebirthEventBase(final int sequence, final JRebirthEventType eventType, final String source, final String target, final String... eventData) {
        this.sequence = sequence;
        this.eventType = eventType;
        this.source = source;
        this.target = target;
        if (eventData.length > 0) {
            this.eventData = eventData[0]; // TODO Manage list of event data
        }
    }

    /**
     * Default Constructor used to parse a string.
     *
     * @param eventSerialized the serialized event
     */
    public JRebirthEventBase(final String eventSerialized) {
        parseString(eventSerialized);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sequence() {
        return this.sequence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRebirthEvent sequence(final int sequence) {
        this.sequence = sequence;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRebirthEventType eventType() {
        return this.eventType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRebirthEvent eventType(final JRebirthEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String source() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRebirthEvent source(final String source) {
        this.source = source;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String target() {
        return this.target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRebirthEvent target(final String target) {
        this.target = target;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String eventData() {
        return this.eventData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRebirthEvent eventData(final String eventData) {
        this.eventData = eventData;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(sequence()).append(ClassUtility.SEPARATOR)
          .append(eventType()).append(ClassUtility.SEPARATOR)
          .append(source()).append(ClassUtility.SEPARATOR)
          .append(target()).append(ClassUtility.SEPARATOR)
          .append(eventData()).append(ClassUtility.SEPARATOR);
        return sb.toString();
    }

    /**
     * Parse the serialized string.
     *
     * @param eventSerialized the serialized string
     */
    private void parseString(final String eventSerialized) {
        final StringTokenizer st = new StringTokenizer(eventSerialized, ClassUtility.SEPARATOR);
        if (st.countTokens() >= 5) {
            sequence(Integer.parseInt(st.nextToken()))
                                                      .eventType(JRebirthEventType.valueOf(st.nextToken()))
                                                      .source(st.nextToken())
                                                      .target(st.nextToken())
                                                      .eventData(st.nextToken());
        }
    }

    // /**
    // * Return the class object or null.
    // *
    // * @param className the class name to convert into class object
    // *
    // * @return a class or null
    // */
    // private Class<?> getClass(final String className) {
    // Class<?> cls = null;
    // try {
    // if (!"null".equals(className)) {
    // cls = Class.forName(className);
    // }
    // } catch (final ClassNotFoundException e) {
    // cls = Object.class;
    // }
    // return cls;
    // }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public String getKey() {
    // return toString();
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public Object getValue() {
    // // CHECK IT
    // return null;
    // }

}
