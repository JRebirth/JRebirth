package ui;

import javafx.event.ActionEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.ui.adapter.ActionAdapter;

/**
 * The class <strong>TransitionController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class TransitionController extends DefaultController<TransitionModel, TransitionView> implements ActionAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public TransitionController(final TransitionView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(ActionEvent actionEvent) {
        // Nothing to do yet
    }

}
