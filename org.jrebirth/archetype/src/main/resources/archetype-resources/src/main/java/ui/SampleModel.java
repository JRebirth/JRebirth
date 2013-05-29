#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleModel</strong>.
 * 
 * @author
 */
public class SampleModel extends AbstractModel<SampleModel, SampleView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleModel.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Put the code to initialize your model here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Put the code to initialize inner models here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customBind() {
        // Put the code to manage model object binding (if any)
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Process a wave action, you must listen the wave type before
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customShowView() {
        // Custom code to process when the view is displayed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customHideView() {
        // Custom code to process when the view is hidden
    }

}
