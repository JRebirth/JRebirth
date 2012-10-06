package org.jrebirth.core.command.basic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>AttachModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class AttachModelCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        final Pane parentNode = getWaveBean(wave).getParentNode();
        final Node createdNode = getWaveBean(wave).getCreatedNode();

        parentNode.getChildren().add(createdNode);
        createdNode.requestFocus();
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
