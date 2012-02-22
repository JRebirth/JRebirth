package org.jrebirth.core.ui.impl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

/**
 * The class <strong>AbstractFXMLController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public abstract class AbstractFXMLController implements Initializable {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resource) {
        System.out.println("Initialize fxml node : " + url.toString());
    }

}
