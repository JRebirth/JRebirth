package org.jrebirth.presentation.ui.classic;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.template.AbstractTemplateView;

/**
 * 
 * The class <strong>ClassicView</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ClassicView extends AbstractTemplateView<ClassicModel, BorderPane, ClassicController> {

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public ClassicView(final ClassicModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getContentPanel() {
        return buildDefaultContent(getModel().getDefaultContent());
    }

}
