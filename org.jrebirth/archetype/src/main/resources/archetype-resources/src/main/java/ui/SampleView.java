#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.BorderPane;

import ${groupId}.core.exception.CoreException;
import ${groupId}.core.ui.AbstractView;

/**
 * The class <strong>SampleView</strong>.
 * 
 * @author
 */
public class SampleView extends AbstractView<SampleModel, BorderPane, SampleController> {

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public SampleView(final SampleModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        getRootNode().setCenter(LabelBuilder.create().text("JRebirth Sample").build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        // Nothing to do yet

    }

}