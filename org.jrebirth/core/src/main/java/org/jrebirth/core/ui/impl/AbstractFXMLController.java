package org.jrebirth.core.ui.impl;

import java.net.URL;
import java.util.ResourceBundle;

import org.jrebirth.core.ui.View;

/**
 * The class <strong>AbstractFXMLController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public abstract class AbstractFXMLController implements FXMLController {

    /** The linked view taht load this FXML component. */
    private View<?, ?, ?> view;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(View<?, ?, ?> view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View<?, ?, ?> getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resource) {
        getView().getModel().getLocalFacade().getGlobalFacade().getLogger().trace("Initialize fxml node : " + url.toString());
    }

}
