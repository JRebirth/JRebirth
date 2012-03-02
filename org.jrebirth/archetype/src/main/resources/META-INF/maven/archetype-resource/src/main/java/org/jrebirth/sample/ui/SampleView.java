package org.jrebirth.sample.ui;

import javafx.scene.layout.BorderPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.impl.AbstractView;

/**
 * The class <strong>SampleView</strong>.
 * 
 * @author
 *  
 * @version 
 * @since 
 */
public abstract class SampleView extends AbstractView<SampleModel, BorderPane, SampleController> {

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public SampleView(final SampleModel model) throws CoreException {
        super(model);
    }

}
