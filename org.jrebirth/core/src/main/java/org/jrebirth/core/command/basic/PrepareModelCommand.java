package org.jrebirth.core.command.basic;

import javafx.scene.Node;

import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>PrepareModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class PrepareModelCommand extends DefaultCommand {

    // FIXME must be run into another thread

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        final Class<? extends Model> first = getWaveBean(wave).getModelClass();

        // ShowModelCommandItem.modelClass;//.getAnnotation(DataClass.class);
        /*
         * if (first == null) { throw new CoreException("No First Model Class defined."); }
         */

        // Build the first root node
        final Node modelNode = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(first).getView().getRootNode();
        getWaveBean(wave).setCreatedNode(modelNode);
    }

    /**
     * Get the wave bean and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return he casted wave bean
     */
    public ShowModelWaveBean getWaveBean(final Wave wave) {
        return (ShowModelWaveBean) wave.getWaveBean();
    }

}
