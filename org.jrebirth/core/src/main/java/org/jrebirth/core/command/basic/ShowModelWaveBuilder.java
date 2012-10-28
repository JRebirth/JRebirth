package org.jrebirth.core.command.basic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.CommandWaveBuilder;
import org.jrebirth.core.wave.WaveBase;

/**
 * The class <strong>ShowModelWaveBuilder</strong>. is used to build a new Show Model Wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <B> The builder generic type
 */
public final class ShowModelWaveBuilder<B extends ShowModelWaveBuilder<B>> extends CommandWaveBuilder<B, ShowModelWaveBean> {

    /** The field used to store the property mask. */
    private int setMask;

    /** The model class to show. */
    private Class<? extends Model> modelClass;

    /** The parent node. */
    private Pane parentNode;

    /** The created node. */
    private Node createdNode;

    /**
     * Private constructor.
     */
    private ShowModelWaveBuilder() {
        super(ShowModelCommand.class, ShowModelWaveBean.class);
    }

    /**
     * Static method to build a default builder.
     * 
     * @return a new fresh ShowModelWaveBuilder instance
     */
    @SuppressWarnings("rawtypes")
    public static ShowModelWaveBuilder<?> create() {
        return new ShowModelWaveBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(final WaveBase paramWave) {
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

    /**
     * Define the parent node.
     * 
     * @param parentNode the node that will hold the model to shown
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B parentNode(final Pane parentNode) {
        this.parentNode = parentNode;
        this.setMask |= 1;
        return (B) this;
    }

    /**
     * Define the model class to shown.
     * 
     * @param modelClass the model class to shown
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B modelClass(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
        this.setMask |= 2;
        return (B) this;
    }

    /**
     * Define the created node.
     * 
     * @param createdNode the node created
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B createdNode(final Node createdNode) {
        this.createdNode = createdNode;
        this.setMask |= 4;
        return (B) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveBase build() {
        final WaveBase localWave = new WaveBase();
        applyTo(localWave);
        return localWave;
    }

}
