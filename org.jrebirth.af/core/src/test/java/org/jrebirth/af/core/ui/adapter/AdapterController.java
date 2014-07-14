package org.jrebirth.af.core.ui.adapter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.View;

public class AdapterController extends DefaultController implements ActionAdapter {

    private final Button button;

    public AdapterController(final View view) throws CoreException {
        super(view);

        this.button = new Button();
    }

    /**
     * @return Returns the button.
     */
    protected Button getButton() {
        return this.button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {
        super.initEventHandlers();

        this.button.setOnAction(getHandler(ActionEvent.ACTION));
    }

}
