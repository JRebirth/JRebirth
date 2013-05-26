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
package org.jrebirth.core.resource.parameter;

import org.jrebirth.core.resource.AbstractBaseParams;
import org.jrebirth.core.resource.font.CustomFontName;
import org.jrebirth.core.resource.font.FamilyFont;
import org.jrebirth.core.resource.font.FontName;
import org.jrebirth.core.resource.font.RealFont;

/**
 * The interface <strong>ObjectParameter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <O> the parameter type
 */
public class ObjectParameter<O extends Object> extends AbstractBaseParams implements ParameterParams {

    /** The parameter name. */
    private String parameterName;

    /** The parameter object. */
    private final O object;

    /**
     * Default Constructor.
     * 
     * @param object the parameter object
     */
    public ObjectParameter(final O object) {
        super();
        this.object = object;
    }

    /**
     * Default Constructor.
     * 
     * @param parameterName the name of the parameter
     * @param object the parameter object
     */
    public ObjectParameter(final String parameterName, final O object) {
        super();
        this.parameterName = parameterName;
        this.object = object;
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
     * @param serializedObject the string object to convert
     * 
     * @return the real object
     */
    public Object parseObject(final String serializedObject) {
        Object res = null;
        if (this.object instanceof String) {
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
        } else if (this.object instanceof FontName) {
            res = new CustomFontName(serializedObject);
        } else if (this.object instanceof RealFont) {
            res = new CustomFontName(serializedObject);
        } else if (this.object instanceof FamilyFont) {
            res = new CustomFontName(serializedObject);
        }

        return res;
    }
}
