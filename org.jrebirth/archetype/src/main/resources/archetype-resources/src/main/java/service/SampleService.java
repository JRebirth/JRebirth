#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.core.exception.CoreException;
import ${groupId}.core.service.impl.ServiceImpl;

/**
 * The class <strong>SampleService</strong>.
 * 
 * @author
 * 
 * @version
 * @since
 */
public final class SampleService extends ServiceImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        // Initialize the service
    }

    // Provide service methods
}
