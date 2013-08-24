package org.jrebirth.core.wave;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.jrebirth.core.wave.Wave.Status;

/**
 * The class <strong>WaveStatusChangeListener</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface WaveStatusChangeListener extends ChangeListener<Wave.Status> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void changed(ObservableValue<? extends Status> observable, Status oldValue, Status newValue);
}
