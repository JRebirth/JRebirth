package org.jrebirth.core.wave;

import org.jrebirth.core.command.Command;

/**
 * The class <strong>WaveBuilder</strong>.
 * 
 * Base builder used to build a custom wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <B> The builder generic type
 * @param <WB> the waveBean sub type to use
 */
public class CommandWaveBuilder<B extends CommandWaveBuilder<B, WB>, WB extends WaveBean> extends WaveBuilder<B> {

    /** The class of the command to launch. */
    private final Class<? extends Command> commandClass;

    /** The class of the wave bean to use. */
    private final Class<WB> waveBeanClass;

    /**
     * Default Constructor.
     * 
     * @param commandClass the command class to use
     */
    @SuppressWarnings("unchecked")
    public CommandWaveBuilder(final Class<? extends Command> commandClass) {
        this(commandClass, (Class<WB>) DefaultWaveBean.class);
    }

    /**
     * Default Constructor.
     * 
     * @param commandClass the class of the command to launch
     * @param waveBeanClass the class of the wave bean to use
     */
    public CommandWaveBuilder(final Class<? extends Command> commandClass, final Class<WB> waveBeanClass) {
        super();
        this.commandClass = commandClass;
        this.waveBeanClass = waveBeanClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(final WaveBase paramWave) {
        super.applyTo(paramWave);

        paramWave.setWaveGroup(WaveGroup.CALL_COMMAND);
        paramWave.setRelatedClass(this.commandClass);
        paramWave.setWaveBeanClass(this.waveBeanClass);
    }

    /**
     * Get the wave Bean from the wave and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return the casted wavebean
     */
    protected WB getWaveBean(final Wave wave) {
        return (WB) wave.getWaveBean();
    }

}
