package org.jrebirth.analyzer.ui.properties;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 * 
 * The class <strong>PropertiesView</strong>.
 * 
 * The view used to display properties of a selected node.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class PropertiesView extends DefaultView<PropertiesModel, VBox, PropertiesController> {

    /** The Node name. */
    private Text nodeName;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public PropertiesView(final PropertiesModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        getRootNode().setMinWidth(200);

        final HBox hbox = HBoxBuilder.create().build();
        final Label label = new Label("Node Name :");
        this.nodeName = new Text();
        hbox.getChildren().addAll(label, this.nodeName);

        getRootNode().getChildren().add(hbox);
    }

    /**
     * @return Returns the nodeName.
     */
    Text getNodeName() {
        return this.nodeName;
    }
}
