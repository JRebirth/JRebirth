package org.jrebirth.af.core.wave;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.jrebirth.af.core.wave.Wave.Status;

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
    void changed(final ObservableValue<? extends Status> observable, final Status oldValue, final Status newValue);
}
