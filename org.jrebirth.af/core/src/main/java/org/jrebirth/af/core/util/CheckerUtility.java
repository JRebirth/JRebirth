package org.jrebirth.af.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.jrebirth.af.core.wave.WaveTypeRegistry;

/**
 * The class <strong>CheckerUtility</strong>.
 *
 * Some Useful method utilities to check if API is respected.
 *
 * @author Sébastien Bordes
 */
public final class CheckerUtility implements UtilMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(CheckerUtility.class);

    /**
     * Private Constructor.
     */
    private CheckerUtility() {
        // Nothing to do
    }

    /**
     * Check if wave Type contract is respected for the the given {@link EnhancedComponent} class.
     *
     * Throws a Runtime exception is Wave Contract is broken.
     *
     * @param waveReadyClass the {@link EnhancedComponent} class to check
     * @param waveTypes the contract to respect (could be several WaveType)
     */
    public static void checkWaveTypeContract(final Class<? extends Component> waveReadyClass, final WaveType... waveTypes) {

        // Perform the check only if Developer Mode is activated
        if (JRebirthParameters.DEVELOPER_MODE.get()) {

            for (final WaveType waveType : waveTypes) {

                String methodName = ClassUtility.underscoreToCamelCase(waveType.toString());
                final List<Method> methods = ClassUtility.retrieveMethodList(waveReadyClass, waveType.toString());
                final List<Method> annotatedMethods = ClassUtility.getAnnotatedMethods(waveReadyClass, OnWave.class);

                if (methods.size() < 1 && annotatedMethods.size() < 1) {
                    LOGGER.log(BROKEN_API_NO_METHOD, waveReadyClass.getSimpleName(), methodName);
                    LOGGER.log(WAVE_HANDLER_METHOD_REQUIRED, waveReadyClass.getSimpleName(), methodName, waveType.items());
                }

                // Check parameter only for a WaveType

                int methodParameters = 0;
                boolean hasCompliantMethod = false;

                final List<WaveItem<?>> wParams = waveType.items();

                for (int j = 0; j < methods.size() && !hasCompliantMethod; j++) {
                    hasCompliantMethod = checkMethodSignature(methods.get(j), wParams);
                    if (!hasCompliantMethod) {
                        methodParameters = methods.get(j).getParameterTypes().length - 1; // Remove the wave parameters
                    }
                }

                if (!hasCompliantMethod) {
                    for (int j = 0; j < annotatedMethods.size() && !hasCompliantMethod; j++) {
                        if (WaveTypeRegistry.getWaveType(annotatedMethods.get(j).getAnnotation(OnWave.class).value()) == waveType) {
                            hasCompliantMethod = checkMethodSignature(annotatedMethods.get(j), wParams);
                            if (!hasCompliantMethod) {
                                methodName = annotatedMethods.get(j).getName();
                                methodParameters = annotatedMethods.get(j).getParameterTypes().length - 1; // Remove the wave parameters
                            }
                        }
                    }
                }

                if (!hasCompliantMethod) {
                    LOGGER.log(BROKEN_API_WRONG_PARAMETERS, waveReadyClass.getSimpleName(), methodName,
                               waveType.items().size(), methodParameters);

                    LOGGER.log(WAVE_HANDLER_METHOD_REQUIRED, waveReadyClass.getSimpleName(),
                               methodName, waveType.items());

                    throw new CoreRuntimeException(BROKEN_API_WRONG_PARAMETERS.getText(waveReadyClass.getSimpleName(),
                                                                                       methodName,
                                                                                       waveType.items().size(),
                                                                                       methodParameters));
                }
            }
        }
    }

    /**
     * Compare method parameters with wave parameters.
     *
     * @param method the method to check
     * @param wParams the wave parameters taht define the contract
     *
     * @return true if the method has the right signature
     */
    private static boolean checkMethodSignature(final Method method, final List<WaveItem<?>> wParams) {
        boolean isCompliant = false;

        final Type[] mParams = method.getGenericParameterTypes();

        if (wParams.isEmpty() && Wave.class.isAssignableFrom(ClassUtility.getClassFromType(mParams[0]))) {
            isCompliant = true;
        } else if (mParams.length - 1 == wParams.size()) {

            // Flag used to skip a method not compliant
            boolean skipMethod = false;
            // Check each parameter
            for (int i = 0; !skipMethod && i < mParams.length - 1 && !isCompliant; i++) {
                if (!ClassUtility.getClassFromType(mParams[i]).isAssignableFrom(ClassUtility.getClassFromType(wParams.get(i).getItemType()))) {
                    // This method has not the right parameters
                    skipMethod = true;
                }
                if (i == mParams.length - 2
                        && Wave.class.isAssignableFrom(ClassUtility.getClassFromType(mParams[i + 1]))) {
                    // This method is compliant with wave type
                    isCompliant = true;
                }
            }
        }
        return isCompliant;
    }

    /**
     * Check that wave has all data required by the WaveType contract (if any).
     *
     * Data can be stored into WaveData wrapper or WaveBean
     *
     * @param wave the wave to check
     *
     */
    public static void checkWave(final Wave wave) {
        if (JRebirthParameters.DEVELOPER_MODE.get()) {
            if (wave.waveType() != null) {

                // List missing wave items not held by WaveData wrapper
                final List<WaveItem<?>> missingWaveItems = new ArrayList<>();
                for (final WaveItem<?> item : wave.waveType().items()) {

                    if (!wave.contains(item)) {
                        missingWaveItems.add(item);
                    }

                }

                // Check provided WaveBean (if any) EXPERIMENTAL
                if (wave.waveBean() != null) {

                    // Check that WaveItem not present into WaveData wrapper are available into available WaveBean
                    for (int i = missingWaveItems.size() - 1; i >= 0; i--) {
                        final WaveItem<?> missing = missingWaveItems.get(i);

                        if (missing.getName() != null && !missing.getName().isEmpty()) {

                            final Field property = ClassUtility.findProperty(wave.waveBean().getClass(), missing.getName(), (Class<?>) missing.getItemType());

                            if (property != null) {
                                missingWaveItems.remove(missing);
                            }
                        }
                    }
                }

                // Log informative message and throw an error
                if (!missingWaveItems.isEmpty()) {

                    LOGGER.log(BROKEN_WAVE_SENT, wave.toString());

                    // List all missing wave items
                    final StringBuilder sb = new StringBuilder();
                    for (final WaveItem<?> missing : missingWaveItems) {
                        sb.append(missing.toString());
                    }

                    LOGGER.log(BROKEN_WAVE_BAD_ITEM_LIST, sb.toString());

                    throw new CoreRuntimeException(BROKEN_WAVE_BAD_ITEM_LIST.getText(sb.toString()));
                }
            }
        }
    }
}
