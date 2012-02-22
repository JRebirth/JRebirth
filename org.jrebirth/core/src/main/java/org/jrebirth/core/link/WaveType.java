package org.jrebirth.core.link;

/**
 * 
 * The interface <strong>WaveType</strong>.
 * 
 * A WaveType could be an enumeration that return an action name.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface WaveType {

    /**
     * Return the name of the wave type, commonly bound on enum.name() method.
     * 
     * @return the name of the wave type
     */
    String getName();

    /**
     * Return the method to process in the processor class.
     * 
     * @return a method name or null
     */
    String getAction();

}
