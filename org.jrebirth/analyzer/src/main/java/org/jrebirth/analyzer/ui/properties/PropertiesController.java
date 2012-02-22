package org.jrebirth.analyzer.ui.properties;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.impl.DefaultController;

/**
 * The class <strong>PropertiesController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class PropertiesController extends DefaultController<PropertiesModel, PropertiesView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public PropertiesController(final PropertiesView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Nothing to do yet

    }

}
