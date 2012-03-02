package org.jrebirth.sample.ui;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.impl.AbstractController;

/**
 * The class <strong>SampleController</strong>.
 * 
 * @author 
 * 
 * @version 
 * @since
 */
public class SampleController extends AbstractController<SampleModel, SampleView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public SampleController(final SampleView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {
        super.customInitializeEventAdapters();

        // Attach event adapters
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        super.customInitializeEventHandlers();

        // Listen events
        
    }


}
