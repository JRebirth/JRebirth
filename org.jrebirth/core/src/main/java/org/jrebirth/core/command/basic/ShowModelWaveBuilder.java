package org.jrebirth.core.command.basic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.CommandWaveBuilder;
import org.jrebirth.core.wave.WaveBase;

/**
 * The class <strong>ShowModelWaveBuilder</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ShowModelWaveBuilder<B extends ShowModelWaveBuilder<B>> extends CommandWaveBuilder<B, ShowModelWaveBean> {

    private int setMask;

    private Class<? extends Model> modelClass;

    /** The parent node. */
    private Pane parentNode;

    /** The created node. */
    private Node createdNode;

    @SuppressWarnings("unchecked")
    public static ShowModelWaveBuilder<?> create()
    {
        return new ShowModelWaveBuilder();
    }

    public ShowModelWaveBuilder() {
        super(ShowModelCommand.class, ShowModelWaveBean.class);
    }

    @Override
    public void applyTo(final WaveBase paramWave)
    {
        super.applyTo(paramWave);

        final int i = this.setMask;
        if ((i & 0x1) != 0) {
            getWaveBean(paramWave).setParentNode(this.parentNode);
        }
        if ((i & 0x2) != 0) {
            getWaveBean(paramWave).setModelClass(this.modelClass);
        }
        if ((i & 0x4) != 0) {
            getWaveBean(paramWave).setCreatedNode(this.createdNode);
        }

    }

    public B parentNode(final Pane parentNode)
    {
        this.parentNode = parentNode;
        this.setMask |= 1;
        return (B) this;
    }

    public B modelClass(final Class<? extends Model> modelClass)
    {
        this.modelClass = modelClass;
        this.setMask |= 2;
        return (B) this;
    }

    public B createdNode(final Node createdNode)
    {
        this.createdNode = createdNode;
        this.setMask |= 4;
        return (B) this;
    }

    @Override
    public WaveBase build()
    {
        final WaveBase localWave = new WaveBase();
        applyTo(localWave);
        return localWave;
    }

}
