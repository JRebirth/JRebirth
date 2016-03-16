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
package org.jrebirth.af.core.ui.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.annotation.type.Action;
import org.jrebirth.af.api.ui.annotation.type.EnumEventType;
import org.jrebirth.af.api.ui.annotation.type.Key;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.ui.UIMessages;
import org.jrebirth.af.core.util.ClassUtility;

/**
 * The interface <strong>AnnotationEventHandler</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <E> the event type to handle
 */
public class AnnotationEventHandler<E extends Event> extends AbstractNamedEventHandler<E> implements EventHandler<E>, UIMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AnnotationEventHandler.class);

    /** The OnXxxxx annotation that define which handler to manage. */
    private final Annotation annotation;

    /** The object used to handle the triggered event. */
    private final Object callbackObject;

    /**
     * Default Constructor.
     *
     * @param callbackObject the object that will handle the event by reflection
     * @param annotation the annotation that configure the event handling
     *
     * @throws CoreException if handler is not correctly initialized
     */
    public AnnotationEventHandler(final Object callbackObject, final Annotation annotation) throws CoreException {
        super(AnnotationEventHandler.class.getSimpleName() + "-" + annotation.annotationType().getName());

        this.callbackObject = callbackObject;
        this.annotation = annotation;

        if (CoreParameters.DEVELOPER_MODE.get()) {
            checkCallbackMethods();
        }
    }

    /**
     * For each annotation event type, check if the callback method exists.
     *
     * @throws CoreException an exception if the current class doesn't have the right handling method
     */
    private void checkCallbackMethods() throws CoreException {

        for (final EnumEventType eet : getAnnotationValue()) {

            final String methodName = buildHandlingMethodName(eet);

            final Class<?> eventClass = getAnnotationApiEventClass();

            try {
                this.callbackObject.getClass().getDeclaredMethod(methodName, eventClass);
            } catch (NoSuchMethodException | SecurityException e) {
                throw new CoreException(NO_EVENT_CALLBACK.getText(this.callbackObject.getClass().getName(), methodName, eventClass.getName()), e);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Event event) {

        final String methodName = buildHandlingMethodName(convertEventToEnum(event.getEventType()));
        if (!methodName.isEmpty()) {
            callMethod(methodName, event);
        }

    }

    /**
     * Build the handling method name used to manage this event.
     *
     * @param annotationType the custom annotation event type used to define the kind of event to manage
     *
     * @return the method name to trigger into the callback object
     */
    private String buildHandlingMethodName(final EnumEventType annotationType) {

        final StringBuilder methodName = new StringBuilder();
        if (Arrays.asList(getAnnotationValue()).contains(annotationType)) {

            // Lower case the first letter of the annotation name
            methodName.append(this.annotation.annotationType().getSimpleName().substring(0, 1).toLowerCase());
            // Don't change the case for all other letters
            methodName.append(this.annotation.annotationType().getSimpleName().substring(1));
            // Append if necessary the sub type if not equals to any
            methodName.append(Key.Any.name().equals(annotationType.toString()) || Action.Action.name().equals(annotationType.toString()) ? "" : annotationType.name());

            // Add suffix if handling method is named
            final String uniqueName = getAnnotationName();
            if (uniqueName.length() > 0) {
                methodName.append(uniqueName);
            }
        }
        return methodName.toString();
    }

    /**
     * Return the name of the annotation to define the unique callback method name.
     *
     * @return the annotation name attribute
     */
    private String getAnnotationName() {
        return ClassUtility.getAnnotationAttribute(this.annotation, "name").toString();
    }

    /**
     * Return the value of the annotation used to define type of handler to manage.
     *
     * @return the annotation value attribute
     */
    private EnumEventType[] getAnnotationValue() {
        return (EnumEventType[]) ClassUtility.getAnnotationAttribute(this.annotation, "value");
    }

    /**
     * Return the api event class used by the annotation.
     *
     * @return the annotation apiEventClass attribute
     */
    @SuppressWarnings("unchecked")
    private Class<Event> getAnnotationApiEventClass() {
        return (Class<Event>) ClassUtility.getAnnotationAttribute(this.annotation, "apiEventClass");
    }

    /**
     * Convert a JavaFX event type into an annotation event type.
     *
     * @param eventType the JavaFX event type
     *
     * @return the Annotation event type or null if not found
     */
    private EnumEventType convertEventToEnum(final EventType<? extends Event> eventType) {

        EnumEventType convertedType = null;

        if (getAnnotationValue() != null && getAnnotationValue().length > 0) {
            // Retrieve all annotation event types
            final EnumEventType[] aTypes = getAnnotationValue()[0].getClass().getEnumConstants();
            // Parse all annotation event types
            for (int i = 0; i < aTypes.length && convertedType == null; i++) {
                // Find the corresponding annotation event type if its javaFX event type matches
                if (aTypes[i].eventType() == eventType) {
                    convertedType = aTypes[i];
                }
            }
        }
        return convertedType;
    }

    /**
     * Call the method into the callback object.
     *
     * @param methodName the method name to call
     * @param event the event to trigger
     */
    private void callMethod(final String methodName, final Event event) {
        final Class<?> ctrlClass = this.callbackObject.getClass();

        try {
            final Method method = ctrlClass.getDeclaredMethod(methodName, event.getClass());

            ClassUtility.callMethod(method, this.callbackObject, event);

        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | CoreException e) {
            LOGGER.log(EVENT_HANDLING_IMPOSSIBLE, e);
        }

    }
}
