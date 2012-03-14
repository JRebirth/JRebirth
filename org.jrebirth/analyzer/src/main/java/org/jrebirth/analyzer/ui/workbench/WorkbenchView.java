package org.jrebirth.analyzer.ui.workbench;

import javafx.scene.layout.BorderPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 * 
 * The class <strong>WorkbenchView</strong>.
 * 
 * The main view of the JRebirth Analyzer application.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class WorkbenchView extends DefaultView<WorkbenchModel, BorderPane, WorkbenchController> {

    /**
     * Default Constructor.
     * 
     * @param model the view model
     * 
     * @throws CoreException if build fails
     */
    public WorkbenchView(final WorkbenchModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        getRootNode().setPrefSize(800, 600);

        // Attach the controls view to the top place of the root border pane
        getRootNode().setTop(getModel().getInnerModel(WorkbenchInnerModels.CONTROLS).getRootNode());

        // Attach the properties view to the right place of the root border pane
        getRootNode().setRight(getModel().getInnerModel(WorkbenchInnerModels.PROPERTIES).getRootNode());

        // Attach the properties view to the center place of the root border
        // pane
        getRootNode().setCenter(getModel().getInnerModel(WorkbenchInnerModels.EDITOR).getRootNode());
    }

}
