package org.jrebirth.core.wave;

import org.jrebirth.core.command.Command;

/**
 * The class <strong>WaveBuilder</strong>.
 * 
 * Base builder used to build a custom wave.
 * 
 * @author Sébastien Bordes
 */
public class CommandWaveBuilder<B extends CommandWaveBuilder<B, WB>, WB extends WaveBean> extends WaveBuilder<B> {

    private final Class<? extends Command> commandClass;
    private final Class<WB> waveBeanClass;

    /**
     * 
     * Default Constructor.
     * 
     * @param commandClass
     */
    public CommandWaveBuilder(final Class<? extends Command> commandClass) {
        this(commandClass, (Class<WB>) DefaultWaveBean.class);

    }

    /**
     * 
     * Default Constructor.
     * 
     * @param commandClass
     * @param waveBeanClass
     */
    public CommandWaveBuilder(final Class<? extends Command> commandClass, final Class<WB> waveBeanClass) {
        this.commandClass = commandClass;
        this.waveBeanClass = waveBeanClass;
    }

    @Override
    public void applyTo(final WaveBase paramWave)
    {
        super.applyTo(paramWave);

        paramWave.setWaveGroup(WaveGroup.CALL_COMMAND);
        paramWave.setRelatedClass(this.commandClass);
        paramWave.setWaveBeanClass(this.waveBeanClass);
    }

    protected WB getWaveBean(final Wave wave) {
        return (WB) wave.getWaveBean();
    }

}
