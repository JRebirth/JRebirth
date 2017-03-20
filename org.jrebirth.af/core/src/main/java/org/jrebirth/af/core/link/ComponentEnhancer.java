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
package org.jrebirth.af.core.link;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import org.jrebirth.af.api.annotation.AfterInit;
import org.jrebirth.af.api.annotation.BeforeInit;
import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.annotation.OnRelease;
import org.jrebirth.af.api.annotation.SkipAnnotation;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.basic.InnerComponent;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.facade.FacadeReady;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.component.basic.AbstractComponent;
import org.jrebirth.af.core.component.basic.CBuilder;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.util.MultiMap;
import org.jrebirth.af.core.wave.WaveTypeRegistry;

/**
 * The class <strong>ComponentEnhancer</strong> is an utility class used to manage Components Annotations.
 *
 * @author Sébastien Bordes
 */
public final class ComponentEnhancer implements LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ComponentEnhancer.class);

    /**
     * Private Constructor.
     */
    private ComponentEnhancer() {
        // Nothing to do
    }

    /**
     * Check if annotation can be processed for the given class.
     *
     * @param componentClass the class to check
     *
     * @return true if annotation can be processed
     */
    public static boolean canProcessAnnotation(final Class<? extends Component<?>> componentClass) {

        final SkipAnnotation skip = ClassUtility.getLastClassAnnotation(componentClass, SkipAnnotation.class);

        // No annotation or annotation deactivated ==> skip annotation processing
        return !(skip == null || skip.value());
    }

    /**
     * Inject components.
     *
     * @param component the parent component
     */
    public static void injectComponent(final Component<?> component) {
        injectComponent(component, false);
    }

    /**
     * Inject inner components.
     *
     * @param component the parent component
     */
    public static void injectInnerComponent(final Component<?> component) {
        injectComponent(component, true);
    }

    /**
     * Inject component.
     *
     * @param component the component
     * @param inner only manage innercomponent otherwise mange component
     */
    private static void injectComponent(final Component<?> component, final boolean inner) {

        // Retrieve all fields annotated with Link
        for (final Field field : ClassUtility.getAnnotatedFields(component.getClass(), Link.class)) {
            final String keyPart = field.getAnnotation(Link.class).value();
            if (inner) {
                if (InnerComponent.class.isAssignableFrom(field.getType())) {
                    if (keyPart.isEmpty()) {
                        injectInnerComponent(component, field);
                    } else {
                        injectInnerComponent(component, field, keyPart);
                    }
                }
            } else {
                if (Component.class.isAssignableFrom(field.getType())) {
                    if (keyPart.isEmpty()) {
                        injectComponent(component, field);
                    } else {
                        injectComponent(component, field, keyPart);
                    }
                }
            }
        }

    }

    /**
     * Inject a component into the property of an other.
     *
     * @param component the component
     * @param field the field
     * @param keyParts the key parts
     */
    @SuppressWarnings("unchecked")
    private static void injectComponent(final FacadeReady<?> component, final Field field, final Object... keyParts) {

        try {
            if (Command.class.isAssignableFrom(field.getType())) {
                ClassUtility.setFieldValue(field, component, component.localFacade().globalFacade().commandFacade().retrieve((Class<Command>) field.getType(), keyParts));
            } else if (Service.class.isAssignableFrom(field.getType())) {
                ClassUtility.setFieldValue(field, component, component.localFacade().globalFacade().serviceFacade().retrieve((Class<Service>) field.getType(), keyParts));
            } else if (Model.class.isAssignableFrom(field.getType())) {
                ClassUtility.setFieldValue(field, component, component.localFacade().globalFacade().uiFacade().retrieve((Class<Model>) field.getType(), keyParts));
            }
        } catch (IllegalArgumentException | CoreException e) {
            LOGGER.error(COMPONENT_INJECTION_FAILURE, component.getClass(), e);
        }

    }

    /**
     * Inject Inner component.
     *
     * @param component the component
     * @param field the field
     * @param keyParts the key parts
     */
    @SuppressWarnings("unchecked")
    private static void injectInnerComponent(final Component<?> component, final Field field, final Object... keyParts) {

        final ParameterizedType innerComponentType = (ParameterizedType) field.getGenericType();
        final Class<?> componentType = (Class<?>) innerComponentType.getActualTypeArguments()[0];

        try {
            ClassUtility.setFieldValue(field, component, CBuilder.innerComponent((Class<Command>) componentType, keyParts).host(component));

        } catch (IllegalArgumentException | CoreException e) {
            LOGGER.error(COMPONENT_INJECTION_FAILURE, component.getClass(), e);
        }

    }

    // /**
    // * Inject a component into the property of an other.
    // *
    // * @param component the component
    // * @param field the field
    // * @param keyParts the key parts
    // */
    // private static void injectInner(final FacadeReady<?> component, final Field field, final Object... keyParts) {
    //
    // }

    /**
     * Parse all methods to search annotated methods that are attached to a lifecycle phase.
     *
     * @param component the JRebirth component to manage
     *
     * @return the map that store all method that should be call sorted by lifecycle phase
     */
    public static MultiMap<String, Method> defineLifecycleMethod(final Component<?> component) {

        final MultiMap<String, Method> lifecycleMethod = new MultiMap<>();

        manageLifecycleAnnotation(component, lifecycleMethod, BeforeInit.class);
        manageLifecycleAnnotation(component, lifecycleMethod, AfterInit.class);
        manageLifecycleAnnotation(component, lifecycleMethod, OnRelease.class);

        return lifecycleMethod;
    }

    /**
     * Store annotated method related to a lifecycle phase.
     *
     * @param component the JRebirth component to manage
     * @param lifecycleMethod the map that store methods
     * @param annotationClass the annotation related to lifecycle phase
     */
    private static void manageLifecycleAnnotation(final Component<?> component, final MultiMap<String, Method> lifecycleMethod, final Class<? extends Annotation> annotationClass) {
        for (final Method method : ClassUtility.getAnnotatedMethods(component.getClass(), annotationClass)) {

            // Add a method to the multimap entry
            // TODO sort
            lifecycleMethod.add(annotationClass.getName(), method);
        }
    }

    /**
     * Manage {@link OnWave} annotation (defined on type and method).
     *
     * @param component the wave ready
     */
    public static void manageOnWaveAnnotation(final Component<?> component) {

        // Retrieve class annotations (Java 8 add support for repeatable annotations)
        for (final OnWave clsOnWave : component.getClass().getAnnotationsByType(OnWave.class)) {
            manageUniqueWaveTypeAction(component, clsOnWave.value(), null);
        }

        // Iterate over each annotated Method and all annotations
        for (final Method method : ClassUtility.getAnnotatedMethods(component.getClass(), OnWave.class)) {
            for (final OnWave clsOnWave : method.getAnnotationsByType(OnWave.class)) {
                manageUniqueWaveTypeAction(component, clsOnWave.value(), method);
            }
        }

    }

    /**
     * Manage unique {@link WaveType} subscription (from value field of {@link OnWave} annotation).
     *
     * @param component the wave ready
     * @param waveActionName the {@link WaveType} unique name
     * @param method the wave handler method
     */
    private static void manageUniqueWaveTypeAction(final Component<?> component, final String waveActionName, final Method method) {

        // Get the WaveType from the WaveType registry
        final WaveType wt = WaveTypeRegistry.getWaveType(waveActionName);
        if (wt == null) {
            throw new CoreRuntimeException("WaveType '" + waveActionName + "' not found into WaveTypeRegistry.");
        } else {
            // Method is not defined or is the default fallback one
            if (method == null || AbstractComponent.PROCESS_WAVE_METHOD_NAME.equals(method.getName())) {
                // Just listen the WaveType
                component.listen(wt);
            } else {
                // Listen the WaveType and specify the right method handler
                component.listen(null, method, wt);
            }
        }
    }

}
