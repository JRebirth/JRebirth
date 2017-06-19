package org.jrebirth.af.showcase.todos.ui.status;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;

public final class StatusController extends DefaultController<StatusModel, StatusView> {

    public StatusController(final StatusView view) throws CoreException {
        super(view);
    }

}
