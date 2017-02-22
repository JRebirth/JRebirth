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

/**
 * The Class Property.
 */
public class Property extends TypedDefinition<Property> {

    /** The require field. */
    private boolean requireField = true;

    /** The need property. */
    private boolean needProperty = true;

    /** The need getter. */
    private boolean needGetter = true;

    /** The need setter. */
    private boolean needSetter = true;

    private boolean needAddList = true;

    private boolean needRemoveList = true;

    private boolean needPutMap = true;

    private boolean needRemoveMap = true;

    public static Property of(final String name) {
        return new Property().name(name);
    }

    /**
     * @return Returns the requireField.
     */
    public boolean requireField() {
        return this.requireField;
    }

    /**
     * @param requireField The requireField to set.
     */
    public Property requireField(final boolean requireField) {
        this.requireField = requireField;
        return this;
    }

    /**
     * Checks if is need property.
     *
     * @return Returns the needProperty.
     */
    public boolean needProperty() {
        return this.needProperty;
    }

    /**
     * Sets the need property.
     *
     * @param needProperty The needProperty to set.
     */
    public Property needProperty(final boolean needProperty) {
        this.needProperty = needProperty;
        return this;
    }

    /**
     * Checks if is need getter.
     *
     * @return Returns the needGetter.
     */
    public boolean needGetter() {
        return this.needGetter;
    }

    /**
     * Sets the need getter.
     *
     * @param needGetter The needGetter to set.
     */
    public Property needGetter(final boolean needGetter) {
        this.needGetter = needGetter;
        return this;
    }

    /**
     * Checks if is need setter.
     *
     * @return Returns the needSetter.
     */
    public boolean needSetter() {
        return this.needSetter;
    }

    /**
     * Sets the need setter.
     *
     * @param needSetter The needSetter to set.
     */
    public Property needSetter(final boolean needSetter) {
        this.needSetter = needSetter;
        return this;
    }

    public void needAddList(final boolean needAddList) {
        this.needAddList = needAddList;
    }

    public boolean needAddList() {
        return this.needAddList;
    }

    public void needRemoveList(final boolean needRemoveList) {
        this.needRemoveList = needRemoveList;
    }

    public boolean needRemoveList() {
        return this.needRemoveList;
    }

    public void needPutMap(final boolean needPutMap) {
        this.needPutMap = needPutMap;
    }

    public boolean needPutMap() {
        return this.needPutMap;
    }

    public void needRemoveMap(final boolean needRemoveMap) {
        this.needRemoveMap = needRemoveMap;
    }

    public boolean needRemoveMap() {
        return this.needRemoveMap;
    }

    public String getPropertyName() {
        return "p" + getUpperName();
    }

    public String getUpperName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1);
    }

    public String getPropertyType() {

        if (type().qualifiedName().startsWith("java.util.List")) {
            return type().qualifiedName().replace("java.util.List", "javafx.collections.ObservableList");
        } else if (type().qualifiedName().startsWith("java.util.Map")) {
            return type().qualifiedName().replace("java.util.Map", "javafx.collections.ObservableMap");
        } else {
            switch (type().qualifiedName()) {
                case "boolean":
                    return "javafx.beans.property.BooleanProperty";
                case "int":
                    return "javafx.beans.property.IntegerProperty";
                case "long":
                    return "javafx.beans.property.LongProperty";
                case "float":
                    return "javafx.beans.property.FloatProperty";
                case "double":
                    return "javafx.beans.property.DoubleProperty";
                case "string":
                    return "javafx.beans.property.StringProperty";

                default:
                    return "javafx.beans.property.ObjectProperty<" + type() + ">";

            }
        }

        // return null;
    }

    public String getConcretePropertyType() {

        if (type().qualifiedName().startsWith("java.util.List")) {
            return "FXCollections.observableList";
        } else if (type().qualifiedName().startsWith("java.util.Map")) {
            return "FXCollections.observableMap";
        } else {
            switch (type().qualifiedName()) {
                case "boolean":
                    return "javafx.beans.property.SimpleBooleanProperty";
                case "int":
                    return "javafx.beans.property.SimpleIntegerProperty";
                case "long":
                    return "javafx.beans.property.SimpleLongProperty";
                case "float":
                    return "javafx.beans.property.SimpleFloatProperty";
                case "double":
                    return "javafx.beans.property.SimpleDoubleProperty";
                case "string":
                    return "javafx.beans.property.SimpleStringProperty";
                default:
                    return "javafx.beans.property.SimpleObjectProperty<" + type().qualifiedName() + ">";
            }
        }

        // return null;
    }

    public boolean isList() {
        return getConcretePropertyType().contains("observableList");
    }

    public boolean isMap() {
        return getConcretePropertyType().contains("observableMap");
    }

    public String subtype() {
        final String type = type().qualifiedName();
        return type.substring(type.indexOf("<") + 1, type.lastIndexOf(">"));
    }
}
