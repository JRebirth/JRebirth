package org.jrebirth.core.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.concurrent.Task;

import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.Wave.Status;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ServiceTask</strong>.
 * 
 * @author Sébastien Bordes
 */
final class ServiceTask<T> extends Task<T> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTask.class);

    /**
     * The <code>parameterValues</code>.
     */
    private final Object[] parameterValues;
    /**
     * The <code>method</code>.
     */
    private final Method method;
    /**
     * The <code>localService</code>.
     */
    private final ServiceBase service;
    /**
     * The <code>sourceWave</code>.
     */
    private final Wave wave;

    /**
     * Default Constructor only visible by service package.
     * 
     * @param parameterValues the lsit of function parameter
     * @param method the method to call
     * @param localService the service object
     * @param sourceWave the wave to process
     */
    ServiceTask(final ServiceBase localService, final Method method, final Object[] parameterValues, final Wave sourceWave) {
        this.service = localService;
        this.method = method;
        this.parameterValues = parameterValues;
        this.wave = sourceWave;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected T call() throws WaveException {
        T res = null;
        try {
            // Call this method with right parameters
            res = (T) this.method.invoke(this.service, this.parameterValues);

            if (res == null) {
                // No return wave required
                LOGGER.trace(this.service.getClass().getSimpleName() + " Consumes wave (noreturn)" + this.wave.toString());
                this.wave.setStatus(Status.Consumed);
            } else {
                final WaveType responseWaveType = this.service.waveTypeMap.get(this.wave.getWaveType());
                final WaveItem<T> waveItem = (WaveItem<T>) this.service.waveItemMap.get(responseWaveType);

                final Wave returnWave = WaveBuilder.create()
                        .waveType(responseWaveType)
                        .relatedClass(this.getClass())
                        .data(WaveData.build(waveItem, res))
                        .build();
                returnWave.setRelatedWave(this.wave);
                returnWave.addWaveListener(new ServiceWaveListener());

                // Send the return wave to interested components
                this.service.sendWave(returnWave);

            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error("Unable to perform the service task", e);
        }
        return res;
    }
}