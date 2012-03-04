#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import ${groupId}.core.exception.CoreException;
import ${groupId}.core.ui.impl.AbstractController;


/**
 * The class <strong>SampleController</strong>.
 * 
 * @author
 * 
 * @version
 * @since
 */
public class SampleController extends AbstractController<SampleModel, SampleView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public SampleController(final SampleView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {
        // Attach event adapters
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Listen events

    }

}