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
package org.jrebirth.af.core.resource.parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.resource.ResourceParams;
import org.jrebirth.af.api.resource.parameter.ParameterParams;
import org.jrebirth.af.core.resource.AbstractBaseParams;
import org.jrebirth.af.core.resource.ParameterEntry;

/**
 * The interface <strong>ObjectParameter</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <O> the parameter type
 */
public class ObjectParameter<O extends Object> extends AbstractBaseParams implements ParameterParams {

    /** The parameter name. */
    private final String parameterName;

    /** The parameter object. */
    private final O object;

    // /**
    // * Default Constructor.
    // *
    // * @param object the parameter object
    // */
    // public ObjectParameter(final O object) {
    // super();
    // this.object = object;
    //
    // // Object must be not null
    // if (object == null) {
    // throw new CoreRuntimeException("ObjectParameter must have a non null object for unamed parameter");
    // }
    // }

    /**
     * Default Constructor.
     *
     * @param parameterName the name of the parameter
     * @param object the parameter object
     */
    public ObjectParameter(final String parameterName, final O object) {
        super();
        this.object = object;
        this.parameterName = parameterName;

        // Object must be not null
        if (object == null) {
            throw new CoreRuntimeException("ObjectParameter must have a non null object for parameter : " + this.parameterName);
        }
    }

    /**
     * Return the parameter name.
     *
     * @return Returns the parameter name.
     */
    public String name() {
        return this.parameterName;
    }

    /**
     * Return the parameter object value.
     *
     * @return Returns the object.
     */
    public O object() {
        return this.object;
    }

    /**
     * Parse the serialized object.
     *
     * @param parameterEntry the parameter entry to convert that wrap the serialized string
     *
     * @return the real object
     */
    public Object parseObject(final ParameterEntry parameterEntry) {
        Object res = null;
        if (this.object instanceof ResourceParams) {
            // Setup the default object
            ((ResourceParams) this.object).parse(parameterEntry.getSerializedString().split(PARAMETER_SEPARATOR_REGEX));
            // return the new value
            res = this.object;
        } else if (this.object instanceof Class<?>) {
            res = parseClassParameter(parameterEntry.getSerializedString());
        } else if (this.object instanceof Enum<?>) {
            res = parseEnumParameter((Enum<?>) this.object, parameterEntry.getSerializedString());
        } else if (this.object instanceof File) {
            res = parseFileParameter(parameterEntry.getSerializedString());
        } else if (this.object instanceof List<?>) {
            res = parseListParameter(parameterEntry.getSerializedString());
        } else {
            res = parsePrimitive(parameterEntry.getSerializedString());
        }

        // Store the parsed object directly into the entry instance
        // for later access
        parameterEntry.setObject(res);

        return res;
    }

    /**
     * Parse a class definition by calling Class.forName.
     *
     * @param serializedObject the full class name
     *
     * @return the class object
     */
    private Object parseClassParameter(final String serializedObject) {
        Object res = null;
        try {
            res = Class.forName(serializedObject);
        } catch (final ClassNotFoundException e) {
            throw new CoreRuntimeException("Impossible to load class " + serializedObject, e);
        }
        return res;
    }

    /**
     * Parse an Enum definition by calling Enum.valueOf.
     *
     * @param serializedObject the full enumerated value
     *
     * @return the class object
     */
    @SuppressWarnings("unchecked")
    private Object parseEnumParameter(final Enum<?> e, final String serializedObject) {
        final Object res = Enum.valueOf(e.getClass(), serializedObject);
        return res;
    }

    /**
     * Parse a file definition by using canonical path.
     *
     * @param serializedObject the full file path
     *
     * @return the File object
     */
    private Object parseFileParameter(final String serializedObject) {
        final File res = new File(serializedObject);
        if (!res.exists()) {
            throw new CoreRuntimeException("Impossible to load file " + serializedObject);
        }
        return res;
    }

    /**
     * Parse a generic list.
     *
     * @param serializedObject the concatenated list
     *
     * @return the list object
     */
    private Object parseListParameter(final String serializedObject) {
        final List<Object> res = new ArrayList<>();

        for (final String item : serializedObject.split(";")) {
            res.add(item);
        }

        return res;
    }

    /**
     * Parse primitive serialized object.
     *
     * @param serializedObject the serialized string to parse
     *
     * @return a new fresh instance of the object
     */
    private Object parsePrimitive(final String serializedObject) {
        Object res = null;
        if (this.object instanceof Boolean) {
            res = Boolean.valueOf(serializedObject);
        } else if (this.object instanceof String) {
            res = serializedObject;
        } else if (this.object instanceof Character) {
            res = Character.valueOf(serializedObject.charAt(0));
        } else if (this.object instanceof Byte) {
            res = Byte.parseByte(serializedObject);
        } else if (this.object instanceof Short) {
            res = Short.parseShort(serializedObject);
        } else if (this.object instanceof Integer) {
            res = Integer.parseInt(serializedObject);
        } else if (this.object instanceof Long) {
            res = Long.parseLong(serializedObject);
        } else if (this.object instanceof Float) {
            res = Float.parseFloat(serializedObject);
        } else if (this.object instanceof Double) {
            res = Double.parseDouble(serializedObject);
        }
        return res;
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
