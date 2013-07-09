#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleModel</strong>.
 * 
 * @author
 */
public class SampleModel extends DefaultModel<SampleModel, SampleView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleModel.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Put the code to initialize your model here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        LOGGER.debug("Init Sample Model");
        // Put the code to initialize inner models here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {
        // Put the code to manage model object binding (if any)
    }
    
}
