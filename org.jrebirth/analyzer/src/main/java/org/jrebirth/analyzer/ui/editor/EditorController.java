package org.jrebirth.analyzer.ui.editor;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.impl.DefaultController;

/**
 * The class <strong>EditorController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class EditorController extends DefaultController<EditorModel, EditorView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public EditorController(final EditorView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Nothing to do
    }

}
