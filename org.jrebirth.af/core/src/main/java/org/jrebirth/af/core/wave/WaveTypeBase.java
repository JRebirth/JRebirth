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
package org.jrebirth.af.core.wave;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.util.ObjectUtility;

/**
 * The class <strong>WaveType</strong>.
 *
 * @author Sébastien Bordes
 */
public final class WaveTypeBase implements WaveType {

    /** The unique identifier of the wave type. */
    private int uid;

    /**
     * The action to performed, basically the name of the method to call. The keyword "DO_" (by default see {@link CoreParameters.WAVE_HANDLER_PREFIX}) will be prepended to the action name to generate
     * the handler method
     */
    private String action;

    /**
     * The forced prefix defined for this wave type.
     */
    private String prefix;

    /** Define arguments types to use. */
    private final List<WaveItem<?>> waveItemList = new ArrayList<>();

    /** The action called after, basically the name of the method to call. */
    private String returnAction;

    /** Define returned value type (if any). */
    private WaveItem<?> returnItem;

    /** The wave type of the wave returned after processing. */
    private WaveType returnWaveType;

    /** The command to call to process the returned value. */
    private Class<? extends Command> returnCommandClass;

    private Map<Class<? extends Throwable>, Wave> waveExceptionHandler;

    // public static WaveTypeBase create() {
    // return new WaveTypeBase();
    // }
    //
    // /**
    // * Build a wave type.
    // *
    // * @param action The action to perform, "DO_" keyword (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) will be prepended to the action name to generate the handler method
    // *
    // * @param waveItems the list of {@link WaveItem} required by this wave
    // *
    // * @return a new fresh wave type object
    // */
    // public static WaveTypeBase create(final String action/* , final WaveItem<?>... waveItems */) {
    //
    // return Builders.waveType().action(action/* , waveItems */);
    // }

    /**
     * Default constructor.
     */
    WaveTypeBase() {

        // Ensure that the uid will be unique at runtime
        uid(WaveTypeRegistry.getNextUid());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int uid() {
        return this.uid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveTypeBase uid(final int uid) {
        this.uid = uid;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String action() {
        return this.action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveTypeBase action(final String action) {
        WaveTypeRegistry.store(action, this);
        this.action = action;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String prefix() {
        return this.prefix == null ? CoreParameters.WAVE_HANDLER_PREFIX.get() : this.prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType prefix(final String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WaveItem<?>> items() {
        return this.waveItemList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveTypeBase items(final WaveItem<?>... items) {
        // Add each wave item to manage method argument
        for (final WaveItem<?> waveItem : items) {
            this.waveItemList.add(waveItem);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String returnAction() {
        return this.returnAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveTypeBase returnAction(final String returnAction) {
        this.returnAction = returnAction;
        buildReturnWaveType();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveItem<?> returnItem() {
        return this.returnItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveTypeBase returnItem(final WaveItem<?> returnItem) {
        this.returnItem = returnItem;
        // Build the corresponding return wave type only when item is defined
        buildReturnWaveType();
        return this;
    }

    private void buildReturnWaveType() {
        if (this.returnAction != null && this.returnItem != null) {
            returnWaveType(WBuilder.waveType(this.returnAction).items(returnItem()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType returnWaveType() {
        return this.returnWaveType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType returnWaveType(final WaveType returnWaveType) {
        // Also store the return wave type into registry to let it being handled using OnWave annotation
        WaveTypeRegistry.store(returnWaveType.action(), returnWaveType);

        this.returnWaveType = returnWaveType;
        this.returnAction = returnWaveType.action();
        this.returnItem = returnWaveType.items().stream().findFirst().orElse(null);
        return this;
    }

    /**
     * Return the required method parameter list to handle this WaveType.
     *
     * @return the parameter list (Type1 arg1, Type2 arg2 ...)
     */
    public String getItems() {
        final StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (final WaveItem<?> waveItem : items()) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            String fullName = waveItem.type() instanceof ParameterizedType ? ((ParameterizedType) waveItem.type()).toString()
                    : ((Class<?>) waveItem.type()).getName();
            sb.append(fullName).append(" ");

            fullName = fullName.replaceAll("[<>]", "");
            if (waveItem.name() == null || waveItem.name().isEmpty()) {
                sb.append(ObjectUtility.lowerFirstChar(fullName.substring(fullName.lastIndexOf('.') + 1)));
            } else {
                sb.append(waveItem.name());
            }
        }
        return sb.toString();
    }

    // /**
    // * Gets the return wave type.
    // *
    // * @return the return wave type
    // */
    // public WaveType getReturnWaveType() {
    // return this.returnWaveType;
    // }
    //
    // /**
    // * Sets the return wave type.
    // *
    // * @param returnWaveType the new return wave type
    // */
    // public void setReturnWaveType(final WaveType returnWaveType) {
    // this.returnWaveType = returnWaveType;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        // The action name will be used to define the name of the wave handler method
        // Prepend do before the action name to force wave handler method to begin with do (convention parameterizable)
        return prefix() + this.action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object waveType) {
        return waveType instanceof WaveTypeBase && uid() == ((WaveTypeBase) waveType).uid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return uid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Command> returnCommandClass() {
        return this.returnCommandClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType returnCommandClass(final Class<? extends Command> returnCommandClass) {
        this.returnCommandClass = returnCommandClass;
        return this;
    }

    @Override
    public List<WaveItem<?>> parameters() {
        return items().stream().filter(item -> item.isParameter()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Class<? extends Throwable>, Wave> waveExceptionHandler() {
        if (this.waveExceptionHandler == null) {
            this.waveExceptionHandler = new ConcurrentHashMap<>();
        }
        return this.waveExceptionHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType onException(final Class<? extends Command> exceptionCommandClass, final Class<? extends Throwable>... exceptionTypes) {

        final Wave commandWave = WBuilder.callCommand(exceptionCommandClass);

        for (final Class<? extends Throwable> type : exceptionTypes) {
            waveExceptionHandler().put(type, commandWave);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType onException(final WaveType exceptionWaveType, final Class<? extends Throwable>... exceptionTypes) {

        final Wave wave = WBuilder.wave().waveType(exceptionWaveType);

        for (final Class<? extends Throwable> type : exceptionTypes) {
            waveExceptionHandler().put(type, wave);
        }
        return this;
    }

}
