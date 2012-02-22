package org.jrebirth.analyzer.ui.workbench;

import javafx.event.ActionEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.adapter.ActionAdapter;
import org.jrebirth.core.ui.impl.DefaultController;

/**
 * The class <strong>WorkbenchController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class WorkbenchController extends DefaultController<WorkbenchModel, WorkbenchView> implements ActionAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public WorkbenchController(final WorkbenchView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(final ActionEvent actionEvent) {
        // final RotateTransition rt = new RotateTransition(Duration.millis(3000), getView().getHelloButton());
        // rt.setByAngle(180);
        // rt.setCycleCount(4);
        // rt.setAutoReverse(true);
        //
        // rt.play();
        // TODO

    }

}
